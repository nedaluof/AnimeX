package com.nedaluof.animex.data.repository.anime

import com.nedaluof.animex.data.datasource.remote.apiresponse.AnimeListResponse
import retrofit2.Response

/**
 * Created By NedaluOf - 7/7/2023.
 */
interface AnimeRepository {
  /**
   * load anime list
   * @param page / offset of the page
   * @return Response<AnimeListResponse>
   * */
  suspend fun loadAnimeList(page: Int): Response<AnimeListResponse>
}