package com.nedaluof.animex.data.repository.anime

import androidx.paging.PagingSource
import com.nedaluof.animex.data.model.apiresponse.AnimeListResponse
import com.nedaluof.animex.data.model.db.AnimeDataEntity
import com.nedaluof.animex.data.model.db.AnimePagingKey
import retrofit2.Response

/**
 * Created By NedaluOf - 7/7/2023.
 */
interface AnimeRepository {
  /**
   * load anime list from network
   * @param page
   * @return Response<AnimeListResponse>
   * */
  suspend fun loadAnimeList(page: Int): Response<AnimeListResponse>

  /**
   * load anime data list from cache
   * @return PagingSource<Int, Anime>
   * */
  fun loadCachedAnimeList(): PagingSource<Int, AnimeDataEntity>

  /**
   * provide database transaction block to client
   * */
  suspend fun <T> transactionBlock(
    block: suspend () -> T
  ): T?

  /**
   * insert @param anime [list] into anime table
   * */
  suspend fun insertAnimeDataList(list: List<AnimeDataEntity>)

  /**
   * insert @param [pagingKeys] list into paging keys table
   * */
  suspend fun insertAnimePagingKeys(pagingKeys: List<AnimePagingKey>)

  /**
   * get paging keys by its @param [id]
   * */
  suspend fun getAnimePagingKeyByAnimeId(id: Long): AnimePagingKey?

  /**
   * get last paging key creation time
   * */
  suspend fun getLastCreationTimeOfPagingKey(): Long?

  /**
   * clear anime data table
   * */
  suspend fun clearAnimeDataTable()

  /**
   * clear paging keys table
   * */
  suspend fun clearAnimePagingKeyTable()
}