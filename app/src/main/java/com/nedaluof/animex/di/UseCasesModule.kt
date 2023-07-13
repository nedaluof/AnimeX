package com.nedaluof.animex.di

import com.nedaluof.animex.domain.usecase.anime.AnimeListUseCase
import com.nedaluof.animex.domain.usecase.anime.AnimeListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created By NedaluOf - 7/7/2023.
 */
@InstallIn(ViewModelComponent::class)
@Module
abstract class UseCasesModule {

  @ViewModelScoped
  @Binds
  abstract fun bindAnimeListUseCase(
    useCaseImplementation: AnimeListUseCaseImpl
  ): AnimeListUseCase
}