package com.nedaluof.animex.ui.main.anime

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nedaluof.animex.domain.usecase.anime.AnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/7/2023.
 */
@HiltViewModel
class AnimeViewModel @Inject constructor(
  private val stateHandle: SavedStateHandle,
  animeListUseCase: AnimeListUseCase
) : ViewModel() {

  //region variables
  val motionLayoutState = stateHandle.getStateFlow(MOTION_LAYOUT_STATE_KEY, 0F)
  val animeList = animeListUseCase.loadAnimeList(viewModelScope).cachedIn(viewModelScope)
  //endregion

  //region methods
  fun saveMotionLayoutState(
    state: Float
  ) {
    stateHandle[MOTION_LAYOUT_STATE_KEY] = state
  }
  //endregion

  companion object {
    private const val MOTION_LAYOUT_STATE_KEY = "motion_layout_key"
  }
}