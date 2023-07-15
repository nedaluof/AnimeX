package com.nedaluof.animex.domain.usecase.anime

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState.Loading.endOfPaginationReached
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.nedaluof.animex.data.model.apiresponse.AnimeData
import com.nedaluof.animex.data.model.db.AnimePagingKey
import com.nedaluof.animex.data.repository.anime.AnimeRepository
import com.nedaluof.animex.domain.util.getRightException
import timber.log.Timber
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/15/2023.
 */
@OptIn(ExperimentalPagingApi::class)
class AnimeRemoteMediator @Inject constructor(
  private val repository: AnimeRepository
) : RemoteMediator<Int, AnimeData>() {

  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, AnimeData>
  ): MediatorResult {
    val pageOffset: Int = when (loadType) {
      LoadType.REFRESH -> {
        val remoteKeys = getPagingKeyClosestToCurrentPosition(state)
        remoteKeys?.nextKey?.minus(1) ?: 1
      }
      LoadType.PREPEND -> {
        val remoteKeys = getPagingKeyForFirstItem(state)
        val prevKey = remoteKeys?.prevKey
        prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
      }
      LoadType.APPEND -> {
        val remoteKeys = getPagingKeyForLastItem(state)
        val nextKey = remoteKeys?.nextKey
        nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
      }
    }

    try {
      val response = repository.loadAnimeList(pageOffset)
      val animeDataList = response.body()?.data ?: emptyList()
      val isLastPage = animeDataList.isEmpty()

      repository.databaseTransactionBlock {
        if (loadType == LoadType.REFRESH) {
          repository.clearAnimePagingKeyTable()
          repository.clearAnimeTable()
        }

        val prevKey = if (pageOffset > 1) pageOffset - 1 else null
        val nextKey = if (endOfPaginationReached) null else pageOffset + 10
        val pagingKeys = animeDataList.map {
          AnimePagingKey(animeId = it.id, prevKey = prevKey, currentOffset = pageOffset, nextKey = nextKey)
        }
        repository.insertAnimePagingKeys(pagingKeys)
        repository.insertAnimeDataList(animeDataList.onEachIndexed { _, animeData -> animeData.pageOffset = pageOffset })
      }
      return MediatorResult.Success(endOfPaginationReached = isLastPage)
    } catch (exception: Exception) {
      val rightException = getRightException(exception)
      Timber.e("load() ->  ${rightException.message}")
      return MediatorResult.Error(rightException)
    }
  }

  private suspend fun getPagingKeyClosestToCurrentPosition(state: PagingState<Int, AnimeData>): AnimePagingKey? {
    return state.anchorPosition?.let { position ->
      state.closestItemToPosition(position)?.let { animeData ->
        repository.getAnimePagingKeyByAnimeId(animeData.id)
      }
    }
  }

  private suspend fun getPagingKeyForFirstItem(state: PagingState<Int, AnimeData>): AnimePagingKey? {
    return state.pages.firstOrNull {
      it.data.isNotEmpty()
    }?.data?.firstOrNull()?.let { animeData ->
      repository.getAnimePagingKeyByAnimeId(animeData.id)
    }
  }

  private suspend fun getPagingKeyForLastItem(state: PagingState<Int, AnimeData>): AnimePagingKey? {
    return state.pages.lastOrNull {
      it.data.isNotEmpty()
    }?.data?.lastOrNull()?.let { animeData ->
      repository.getAnimePagingKeyByAnimeId(animeData.id)
    }
  }
}