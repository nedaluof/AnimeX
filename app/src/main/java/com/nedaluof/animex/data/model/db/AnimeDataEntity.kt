package com.nedaluof.animex.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nedaluof.animex.data.model.apiresponse.AnimeData
import kotlin.random.Random

/**
 * Created By NedaluOf - 7/17/2023.
 */
@Entity(tableName = "anime_table")
data class AnimeDataEntity(
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0L,
  @ColumnInfo("time_stamp")
  val timeStamp: Long = System.currentTimeMillis() + Random(100).nextInt(),
  @ColumnInfo("anime_data")
  var animeData: AnimeData
)