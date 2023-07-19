package com.nedaluof.animex.data.datasource.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nedaluof.animex.data.model.db.AnimeDataEntity

/**
 * Created By NedaluOf - 7/14/2023.
 */
@Dao
interface AnimeXDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAnimeList(list: List<AnimeDataEntity>)

  @Query("SELECT * FROM anime_table ORDER BY time_stamp ASC")
  fun loadAnimeList(): PagingSource<Int, AnimeDataEntity>

  @Query("DELETE FROM anime_table")
  suspend fun clearAnimeTable()
}