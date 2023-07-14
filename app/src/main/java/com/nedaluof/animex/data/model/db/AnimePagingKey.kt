package com.nedaluof.animex.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created By NedaluOf - 7/14/2023.
 */
@Entity(tableName = "anime_paging_key")
data class AnimePagingKey(
  @PrimaryKey(autoGenerate = false)
  @ColumnInfo(name = "anime_id")
  val animeId: Int,
  val prevKey: Int?,
  val currentOffset: Int,
  val nextKey: Int?,
  @ColumnInfo(name = "created_at")
  val createdAt: Long = System.currentTimeMillis()
)