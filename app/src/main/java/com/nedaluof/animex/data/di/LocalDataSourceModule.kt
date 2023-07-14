package com.nedaluof.animex.data.di

import android.content.Context
import androidx.room.Room
import com.nedaluof.animex.data.datasource.local.AnimeXDao
import com.nedaluof.animex.data.datasource.local.AnimeXDatabase
import com.nedaluof.animex.data.datasource.local.AnimeXPagingKeysDao
import com.nedaluof.animex.data.model.apiresponse.AnimeImage
import com.nedaluof.animex.data.model.apiresponse.AnimeImageTypeConverter
import com.nedaluof.animex.data.model.apiresponse.AttributesTypeConverter
import com.nedaluof.animex.data.model.apiresponse.Dimensions
import com.nedaluof.animex.data.model.apiresponse.DimensionsTypeConverter
import com.nedaluof.animex.data.model.apiresponse.ImageDimension
import com.nedaluof.animex.data.model.apiresponse.ImageDimensionTypeConverter
import com.nedaluof.animex.data.model.apiresponse.LinksTypeConverter
import com.nedaluof.animex.data.model.apiresponse.Meta
import com.nedaluof.animex.data.model.apiresponse.MetaTypeConverter
import com.nedaluof.animex.data.model.apiresponse.RatingFrequencies
import com.nedaluof.animex.data.model.apiresponse.RatingFrequenciesTypeConverter
import com.nedaluof.animex.data.model.apiresponse.Relationship
import com.nedaluof.animex.data.model.apiresponse.RelationshipData
import com.nedaluof.animex.data.model.apiresponse.RelationshipDataTypeConverter
import com.nedaluof.animex.data.model.apiresponse.RelationshipTypeConverter
import com.nedaluof.animex.data.model.apiresponse.Relationships
import com.nedaluof.animex.data.model.apiresponse.RelationshipsTypeConverter
import com.nedaluof.animex.data.model.apiresponse.Titles
import com.nedaluof.animex.data.model.apiresponse.TitlesTypeConverter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By NedaluOf - 7/14/2023.
 */

@InstallIn(SingletonComponent::class)
@Module
object LocalDataSourceModule {

  @Singleton
  @Provides
  fun provideAnimeXDatabase(
    @ApplicationContext context: Context,
    moshi: Moshi
  ): AnimeXDatabase = Room.databaseBuilder(
    context,
    AnimeXDatabase::class.java,
    AnimeXDatabase.DATABASE_NAME
  )
    .addTypeConverter(LinksTypeConverter(moshi))
    .addTypeConverter(AttributesTypeConverter(moshi))
    .addTypeConverter(RelationshipsTypeConverter(moshi))
    .addTypeConverter(RelationshipTypeConverter(moshi))
    .addTypeConverter(RelationshipDataTypeConverter(moshi))
    .addTypeConverter(ImageDimensionTypeConverter(moshi))
    .addTypeConverter(DimensionsTypeConverter(moshi))
    .addTypeConverter(MetaTypeConverter(moshi))
    .addTypeConverter(AnimeImageTypeConverter(moshi))
    .addTypeConverter(RatingFrequenciesTypeConverter(moshi))
    .addTypeConverter(TitlesTypeConverter(moshi))
    .build()

  @Singleton
  @Provides
  fun provideAnimeXDao(
    animeXDatabase: AnimeXDatabase
  ): AnimeXDao = animeXDatabase.getAnimeXDao()

  @Singleton
  @Provides
  fun provideAnimeXPagingKeysDao(
    animeXDatabase: AnimeXDatabase
  ): AnimeXPagingKeysDao = animeXDatabase.getAnimeXPagingKeysDao()

}