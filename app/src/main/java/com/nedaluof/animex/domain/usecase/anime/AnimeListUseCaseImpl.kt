package com.nedaluof.animex.domain.usecase.anime

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.nedaluof.animex.data.model.apiresponse.AnimeData
import com.nedaluof.animex.data.repository.anime.AnimeRepository
import com.nedaluof.animex.domain.base.BaseRemoteUseCase
import com.nedaluof.animex.domain.model.anime.Anime
import com.nedaluof.animex.domain.model.common.ModelMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/7/2023.
 */
class AnimeListUseCaseImpl @Inject constructor(
  private val repository: AnimeRepository,
  private val mapper: ModelMapper<AnimeData, Anime>
) : BaseRemoteUseCase(), AnimeListUseCase {

  /**
   * bump anime paging source result to the ViewModel
   * */
  @OptIn(ExperimentalPagingApi::class)
  override fun loadAnimeList(
    scope: CoroutineScope,
  ): Flow<PagingData<Anime>> {
    return Pager(
      config = PagingConfig(
        pageSize = PAGE_SIZE,
        prefetchDistance = PRE_FETCH_SIZE,
        initialLoadSize = PAGE_SIZE,
        enablePlaceholders = false
      ),
      pagingSourceFactory = { repository.loadCachedAnimeList() },
      remoteMediator = AnimeRemoteMediator(repository)
    ).flow.map {
      it.map { animeData ->
        mapper.fromModel(animeData)!!
      }
    }
  }

  companion object {
    private const val PAGE_SIZE = 10
    private const val PRE_FETCH_SIZE = 20
  }
}