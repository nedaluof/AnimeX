package com.nedaluof.animex.data.repository.anime

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.nedaluof.animex.data.datasource.local.AnimeXDatabase
import com.nedaluof.animex.data.datasource.remote.api.AnimeXApiService
import com.nedaluof.animex.data.model.apiresponse.AnimeListResponse
import com.nedaluof.animex.data.model.db.AnimeDataEntity
import com.nedaluof.animex.data.model.db.AnimePagingKey
import retrofit2.Response
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/7/2023.
 */
class AnimeRepositoryImpl @Inject constructor(
  private val apiService: AnimeXApiService,
  private val database: AnimeXDatabase
) : AnimeRepository {

  //region variables
  private val animeXDao = database.getAnimeXDao()
  private val animeXPagingKeyDao = database.getAnimeXPagingKeysDao()
  //endregion

  //region methods
  /**
   * load anime list over the api
   * @param page
   * @return Response<AnimeListResponse>
   * */
  override suspend fun loadAnimeList(
    page: Int
  ): Response<AnimeListResponse> = apiService.loadAnimeList(page)

  /**
   * load anime data list from cache
   * @param page
   * @return PagingSource<Int, AnimeDataEntity>
   * */
  override fun loadCachedAnimeList(): PagingSource<Int, AnimeDataEntity> =
    animeXDao.loadAnimeList()

  /**
   * provide database transaction block to client
   * @param block
   * @return [T]? coroutine dispatcher block
   * */
  override suspend fun <T> transactionBlock(
    block: suspend () -> T
  ): T? {
    return database.withTransaction {
      return@withTransaction block()
    }
  }

  /**
   * insert list of [AnimeDataEntity] into anime table
   * */
  override suspend fun insertAnimeDataList(list: List<AnimeDataEntity>) {
    animeXDao.insertAnimeList(list)
  }

  /**
   * insert list of [AnimePagingKey] into anime paging keys table
   * */
  override suspend fun insertAnimePagingKeys(pagingKeys: List<AnimePagingKey>) {
    animeXPagingKeyDao.insertAnimePagingKeys(pagingKeys)
  }
  /**
   * get paging key by the anime id
   * */
  override suspend fun getAnimePagingKeyByAnimeId(id: Long): AnimePagingKey? =
    animeXPagingKeyDao.getAnimePagingKeyByAnimeId(id)

  /**
   * get creation time of last anime paging key
   * */
  override suspend fun getLastCreationTimeOfPagingKey(): Long? =
    animeXPagingKeyDao.getLastCreationTime()

  /**
   * clear anime table
   * */
  override suspend fun clearAnimeDataTable() {
    animeXDao.clearAnimeTable()
  }

  /**
   * clear anime paging keys table
   * */
  override suspend fun clearAnimePagingKeyTable() {
    animeXPagingKeyDao.clearAnimePagingKeyTable()
  }
  //endregion
}