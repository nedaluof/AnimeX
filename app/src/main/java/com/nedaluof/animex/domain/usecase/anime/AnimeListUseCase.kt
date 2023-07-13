package com.nedaluof.animex.domain.usecase.anime

import androidx.paging.PagingData
import com.nedaluof.animex.data.datasource.remote.apiresponse.Data
import com.nedaluof.animex.domain.model.anime.Anime
import com.nedaluof.animex.domain.model.common.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

/**
 * Created By NedaluOf - 7/7/2023.
 */
interface AnimeListUseCase {
  /**
   * bump anime paging source result to the ViewModel
   * @param scope
   * @return [Flow] of PagingData the contain anime object
   * */
  fun loadAnimeList(
    scope: CoroutineScope,
  ): Flow<PagingData<Anime>>
}