package com.nedaluof.animex.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nedaluof.animex.data.model.apiresponse.AnimeData
import com.nedaluof.animex.data.model.apiresponse.AnimeImageTypeConverter
import com.nedaluof.animex.data.model.apiresponse.AttributesTypeConverter
import com.nedaluof.animex.data.model.apiresponse.DimensionsTypeConverter
import com.nedaluof.animex.data.model.apiresponse.ImageDimensionTypeConverter
import com.nedaluof.animex.data.model.apiresponse.LinksTypeConverter
import com.nedaluof.animex.data.model.apiresponse.MetaTypeConverter
import com.nedaluof.animex.data.model.apiresponse.RatingFrequenciesTypeConverter
import com.nedaluof.animex.data.model.apiresponse.RelationshipDataTypeConverter
import com.nedaluof.animex.data.model.apiresponse.RelationshipTypeConverter
import com.nedaluof.animex.data.model.apiresponse.RelationshipsTypeConverter
import com.nedaluof.animex.data.model.apiresponse.TitlesTypeConverter
import com.nedaluof.animex.data.model.db.AnimePagingKey

/**
 * Created By NedaluOf - 7/14/2023.
 */
@Database(entities = [AnimeData::class, AnimePagingKey::class], version = 1, exportSchema = false)
@TypeConverters(
  LinksTypeConverter::class,
  LinksTypeConverter::class,
  AttributesTypeConverter::class,
  RelationshipsTypeConverter::class,
  RelationshipTypeConverter::class,
  RelationshipDataTypeConverter::class,
  ImageDimensionTypeConverter::class,
  DimensionsTypeConverter::class,
  MetaTypeConverter::class,
  AnimeImageTypeConverter::class,
  RatingFrequenciesTypeConverter::class,
  TitlesTypeConverter::class
)
abstract class AnimeXDatabase : RoomDatabase() {
  abstract fun getAnimeXDao(): AnimeXDao
  abstract fun getAnimeXPagingKeysDao(): AnimeXPagingKeysDao

  companion object {
    const val DATABASE_NAME = "anime_x_database"
  }
}