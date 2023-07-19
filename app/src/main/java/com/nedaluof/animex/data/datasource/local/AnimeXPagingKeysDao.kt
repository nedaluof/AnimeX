package com.nedaluof.animex.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nedaluof.animex.data.model.db.AnimePagingKey

/**
 * Created By NedaluOf - 7/14/2023.
 */
@Dao
interface AnimeXPagingKeysDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAnimePagingKeys(pagingKeys: List<AnimePagingKey>)

  @Query("Select * From anime_paging_key Where anime_id = :anime_id")
  suspend fun getAnimePagingKeyByAnimeId(anime_id: Long): AnimePagingKey?

  @Query("Delete From anime_paging_key")
  suspend fun clearAnimePagingKeyTable()

  @Query("Select created_at From anime_paging_key Order By created_at DESC LIMIT 1")
  suspend fun getLastCreationTime(): Long?
}