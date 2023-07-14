package com.nedaluof.animex.data.datasource.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nedaluof.animex.data.model.apiresponse.AnimeData

/**
 * Created By NedaluOf - 7/14/2023.
 */
@Dao
interface AnimeXDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAnimeDataList(list: List<AnimeData>)

  @Query("SELECT * FROM anime_table ORDER BY id")
  fun loadAnimeDataList(): PagingSource<Int, AnimeData>

  @Query("DELETE FROM anime_table")
  suspend fun clearAnimeTable()

}