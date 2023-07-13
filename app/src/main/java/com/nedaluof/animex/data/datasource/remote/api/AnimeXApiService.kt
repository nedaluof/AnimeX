package com.nedaluof.animex.data.datasource.remote.api

import com.nedaluof.animex.data.datasource.remote.apiresponse.AnimeListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created By NedaluOf - 7/7/2023.
 */
interface AnimeXApiService {
  @GET("anime")
  suspend fun loadAnimeList(
    @Query("page[limit]") pagelimit: Int ,
    @Query("page[offset]") pageoffset: Int ,
  ): Response<AnimeListResponse>
}