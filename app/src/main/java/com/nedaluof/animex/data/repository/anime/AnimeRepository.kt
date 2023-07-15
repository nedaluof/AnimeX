package com.nedaluof.animex.data.repository.anime

import com.nedaluof.animex.data.model.apiresponse.AnimeData
import com.nedaluof.animex.data.model.apiresponse.AnimeListResponse
import com.nedaluof.animex.data.model.db.AnimePagingKey
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

  /**
   * provide database transaction block to client
   * */
  suspend fun databaseTransactionBlock(
    block: suspend () -> Unit
  )

  /**
   * insert @param anime [list] into anime table
   * */
  suspend fun insertAnimeDataList(list: List<AnimeData>)

  /**
   * insert @param [pagingKeys] list into paging keys table
   * */
  suspend fun insertAnimePagingKeys(pagingKeys: List<AnimePagingKey>)

  /**
   * get paging keys by its @param [id]
   * */
  suspend fun getAnimePagingKeyByAnimeId(id: String): AnimePagingKey?

  /**
   * clear anime table
   * */
  suspend fun clearAnimeTable()
  /**
   * clear paging keys table
   * */
  suspend fun clearAnimePagingKeyTable()
}