package com.nedaluof.animex.di

import com.nedaluof.animex.data.datasource.remote.apiresponse.Data
import com.nedaluof.animex.domain.model.anime.Anime
import com.nedaluof.animex.domain.model.anime.AnimeMapper
import com.nedaluof.animex.domain.model.common.ModelMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By NedaluOf - 7/8/2023.
 */
@InstallIn(ViewModelComponent::class)
@Module
abstract class MappersModule {

  @ViewModelScoped
  @Binds
  abstract fun bindAnimeMapper(
    mapperImplementation: AnimeMapper
  ): ModelMapper<Data, Anime>
}