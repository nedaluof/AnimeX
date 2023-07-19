package com.nedaluof.animex.data.di

import android.content.Context
import androidx.room.Room
import com.nedaluof.animex.data.datasource.local.AnimeXDatabase
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
    @ApplicationContext context: Context
  ): AnimeXDatabase = Room.databaseBuilder(
    context,
    AnimeXDatabase::class.java,
    AnimeXDatabase.DATABASE_NAME
  ).build()

}