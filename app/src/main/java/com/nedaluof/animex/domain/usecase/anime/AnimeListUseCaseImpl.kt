package com.nedaluof.animex.domain.usecase.anime

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nedaluof.animex.data.repository.anime.AnimePagingSource
import com.nedaluof.animex.domain.base.BaseRemoteUseCase
import com.nedaluof.animex.domain.model.anime.Anime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/7/2023.
 */
class AnimeListUseCaseImpl @Inject constructor(
  private val animePagingSource: AnimePagingSource
) : BaseRemoteUseCase(), AnimeListUseCase {

  /**
   * bump anime paging source result to the ViewModel
   * */
  override fun loadAnimeList(
    scope: CoroutineScope,
  ): Flow<PagingData<Anime>> {
    return Pager(
      config = PagingConfig(pageSize = 10, enablePlaceholders = false),
      pagingSourceFactory = { animePagingSource }
    ).flow
  }
}