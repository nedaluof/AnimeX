package com.nedaluof.animex.di

import com.nedaluof.animex.data.repository.anime.AnimeRepositoryImpl
import com.nedaluof.animex.data.repository.anime.AnimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By NedaluOf - 7/7/2023.
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoriesModule {

  @Singleton
  @Binds
  abstract fun bindAnimeRepository(
    repositoryImplementation: AnimeRepositoryImpl
  ): AnimeRepository
}