package com.nedaluof.animex.data.repository.anime

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nedaluof.animex.data.datasource.remote.apiresponse.Data
import com.nedaluof.animex.domain.model.anime.Anime
import com.nedaluof.animex.domain.model.common.ModelMapper
import timber.log.Timber
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/7/2023.
 */
class AnimePagingSource @Inject constructor(
  private val repository: AnimeRepository,
  private val mapper: ModelMapper<Data, Anime>
) : PagingSource<Int, Anime>() {

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
    val startIndex = 0
    val page = params.key ?: startIndex
    return try {
      val response = repository.loadAnimeList(page)
      val animeList = mapper.fromModelList(response.body()?.data ?: emptyList())
      LoadResult.Page(
        data = animeList,
        prevKey = if (page == startIndex) null else page - 10,
        nextKey = if (animeList.isEmpty()) null else page + 10
      )
    } catch (exception: Exception) {
      Timber.e("load() -> exception -> ${exception.localizedMessage}")
      LoadResult.Error(exception)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, Anime>) = null
}