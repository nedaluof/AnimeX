package com.nedaluof.animex.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nedaluof.animex.data.model.apiresponse.AnimeDataConverter
import com.nedaluof.animex.data.model.db.AnimeDataEntity
import com.nedaluof.animex.data.model.db.AnimePagingKey

/**
 * Created By NedaluOf - 7/14/2023.
 */
@Database(entities = [AnimeDataEntity::class, AnimePagingKey::class], version = 1, exportSchema = false)
@TypeConverters(AnimeDataConverter::class)
abstract class AnimeXDatabase : RoomDatabase() {
  abstract fun getAnimeXDao(): AnimeXDao
  abstract fun getAnimeXPagingKeysDao(): AnimeXPagingKeysDao

  companion object {
    const val DATABASE_NAME = "anime_x_database"
  }
}