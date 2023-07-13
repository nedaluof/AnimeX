package com.nedaluof.animex.data.repository.anime

import com.nedaluof.animex.data.datasource.remote.api.AnimeXApiService
import com.nedaluof.animex.data.datasource.remote.apiresponse.AnimeListResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/7/2023.
 */
class AnimeRepositoryImpl @Inject constructor(
  private val apiService: AnimeXApiService
) : AnimeRepository {

  /**
   * load anime list
   * @param page / offset of the page
   * @return Response<AnimeListResponse>
   * */
  override suspend fun loadAnimeList(
    page: Int
  ): Response<AnimeListResponse> =
    apiService.loadAnimeList(PAGE_LIMIT, page)

  companion object {
    private const val PAGE_LIMIT = 20
  }
}