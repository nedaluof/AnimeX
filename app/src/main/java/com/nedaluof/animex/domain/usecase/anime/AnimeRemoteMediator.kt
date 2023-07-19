package com.nedaluof.animex.domain.usecase.anime

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.nedaluof.animex.data.model.db.AnimeDataEntity
import com.nedaluof.animex.data.model.db.AnimePagingKey
import com.nedaluof.animex.data.repository.anime.AnimeRepository
import com.nedaluof.animex.domain.util.getRightException
import kotlinx.coroutines.delay
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Created By NedaluOf - 7/15/2023.
 */
@OptIn(ExperimentalPagingApi::class)
class AnimeRemoteMediator(
  private val repository: AnimeRepository
) : RemoteMediator<Int, AnimeDataEntity>() {

  override suspend fun initialize(): InitializeAction {
    val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
    return if (System.currentTimeMillis() - (repository.getLastCreationTimeOfPagingKey() ?: 0) < cacheTimeout) {
      InitializeAction.SKIP_INITIAL_REFRESH
    } else {
      InitializeAction.LAUNCH_INITIAL_REFRESH
    }
  }

  private suspend fun getPagingKeyClosestToCurrentPosition(state: PagingState<Int, AnimeDataEntity>): AnimePagingKey? {
    return state.anchorPosition?.let { position ->
      state.closestItemToPosition(position)?.let { animeDataEntity ->
        repository.getAnimePagingKeyByAnimeId(animeDataEntity.animeData.animeId)
      }
    }
  }

  private suspend fun getPagingKeyForFirstItem(state: PagingState<Int, AnimeDataEntity>): AnimePagingKey? {
    return state.pages.firstOrNull {
      it.data.isNotEmpty()
    }?.data?.firstOrNull()?.let { animeDataEntity ->
      repository.getAnimePagingKeyByAnimeId(animeDataEntity.animeData.animeId)
    }
  }

  private suspend fun getPagingKeyForLastItem(state: PagingState<Int, AnimeDataEntity>): AnimePagingKey? {
    return state.pages.lastOrNull {
      it.data.isNotEmpty()
    }?.data?.lastOrNull()?.let { animeDataEntity ->
      repository.getAnimePagingKeyByAnimeId(animeDataEntity.animeData.animeId)
    }
  }

  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, AnimeDataEntity>
  ): MediatorResult {
    val currentPage: Int = when (loadType) {
      LoadType.REFRESH -> {
        val pagingKey = getPagingKeyClosestToCurrentPosition(state)
        pagingKey?.nextKey?.minus(1) ?: 1
      }

      LoadType.PREPEND -> {
        val pagingKey = getPagingKeyForFirstItem(state)
        pagingKey?.prevKey ?: return MediatorResult.Success(endOfPaginationReached = pagingKey != null)
      }

      LoadType.APPEND -> {
        val pagingKey = getPagingKeyForLastItem(state)
        pagingKey?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = pagingKey != null)
      }
    }

    try {
      val apiResponse = repository.loadAnimeList(currentPage)
      val animeList = apiResponse.body()?.data ?: emptyList()
      val endOfPaginationReached = animeList.isEmpty()

      //to avoid rate limit error from the api owners
      delay(1000L)

      repository.transactionBlock {
        if (loadType == LoadType.REFRESH) {
          //clear tables if LoadType is REFRESH
          repository.clearAnimePagingKeyTable()
          repository.clearAnimeDataTable()
        }
        //calculate new keys
        val prevKey = if (currentPage > 1) currentPage - 1 else null
        val nextKey = if (endOfPaginationReached) null else currentPage + 1
        Timber.e("new page -> $nextKey")
        val pagingKeys = animeList.map {
          AnimePagingKey(
            animeId = it.animeId,
            prevKey = prevKey,
            currentPage = currentPage,
            nextKey = nextKey
          )
        }

        repository.insertAnimePagingKeys(pagingKeys)
        repository.insertAnimeDataList(animeList.map { AnimeDataEntity(animeData = it) })
      }
      return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
    } catch (exception: Exception) {
      return MediatorResult.Error(getRightException(exception))
    }
  }
}