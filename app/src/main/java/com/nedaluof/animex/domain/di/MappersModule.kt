package com.nedaluof.animex.domain.di

import com.nedaluof.animex.data.model.apiresponse.AnimeData
import com.nedaluof.animex.domain.model.anime.Anime
import com.nedaluof.animex.domain.model.anime.AnimeMapper
import com.nedaluof.animex.domain.model.common.ModelMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

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
  ): ModelMapper<AnimeData, Anime>
}