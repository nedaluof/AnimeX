package com.nedaluof.animex.data.repository.anime

import androidx.room.withTransaction
import com.nedaluof.animex.data.datasource.local.AnimeXDatabase
import com.nedaluof.animex.data.datasource.remote.api.AnimeXApiService
import com.nedaluof.animex.data.model.apiresponse.AnimeData
import com.nedaluof.animex.data.model.apiresponse.AnimeListResponse
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
  val animeXDao = database.getAnimeXDao()
  val animeXPagingKeyDao = database.getAnimeXPagingKeysDao()
  //endregion

  /**
   * load anime list
   * @param page / offset of the page
   * @return Response<AnimeListResponse>
   * */
  override suspend fun loadAnimeList(
    page: Int
  ): Response<AnimeListResponse> =
    apiService.loadAnimeList(PAGE_LIMIT, page)

  /**
   * provide database transaction block to client
   * @param block
   * */
  override suspend fun databaseTransactionBlock(
    block: suspend () -> Unit
  ) {
    database.withTransaction {
      block()
    }
  }

  override suspend fun insertAnimeDataList(list: List<AnimeData>) {
    animeXDao.insertAnimeDataList(list)
  }

  override suspend fun insertAnimePagingKeys(pagingKeys: List<AnimePagingKey>) {
   animeXPagingKeyDao.insertAnimePagingKeys(pagingKeys)
  }

  override suspend fun getAnimePagingKeyByAnimeId(id: String): AnimePagingKey? {
    return animeXPagingKeyDao.getAnimePagingKeyByAnimeId(id)
  }

  override suspend fun clearAnimeTable() {
    animeXDao.clearAnimeTable()
  }

  override suspend fun clearAnimePagingKeyTable() {
    animeXPagingKeyDao.clearAnimePagingKeyTable()
  }

  companion object {
    private const val PAGE_LIMIT = 20
  }
}