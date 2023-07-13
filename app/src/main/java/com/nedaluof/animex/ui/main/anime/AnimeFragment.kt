package com.nedaluof.animex.ui.main.anime

import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.nedaluof.animex.R
import com.nedaluof.animex.databinding.FragmentAnimeBinding
import com.nedaluof.animex.ui.base.PagingLoadStateAdapter
import com.nedaluof.animex.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created By NedaluOf - 7/7/2023.
 */
@AndroidEntryPoint
class AnimeFragment : BaseFragment<FragmentAnimeBinding>(R.layout.fragment_anime) {

  //region variables
  private val animeViewModel by viewModels<AnimeViewModel>()
  private var animePagedAdapter: AnimeAdapter? = null
  private var animeJob: Job? = null
  //end region

  //region view related
  override fun initViews() {
    initAnimeRecyclerView()
  }

  private fun initAnimeRecyclerView() {
    animePagedAdapter =
      AnimeAdapter { anime, pair ->
        val direction = AnimeFragmentDirections.actionAnimeFragmentToAnimeDetailsFragment(anime)
        val extras = FragmentNavigatorExtras(pair)
        findNavController().navigate(direction, extras)
      }.apply {
        with(binding) {
          animeRecyclerView.adapter = withLoadStateFooter(
            footer = PagingLoadStateAdapter { this@apply.retry() }
          )
          addLoadStateListener { loadState ->
            handleShimmer(loadState.refresh is LoadState.Loading)
              val error = when {
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
              }
              error?.let {
                if (snapshot().isEmpty()) {
                  Toast.makeText(
                    requireActivity(),
                    error.error.message.toString(),
                    Toast.LENGTH_LONG
                  ).show()
                }
              }
            }

          postponeEnterTransition()
          animeRecyclerView.doOnPreDraw {
            startPostponedEnterTransition()
          }
        }
      }
  }

  private fun handleShimmer(
    show: Boolean
  ) {
    with(binding) {
      with(shimmerView) {
        if (show) {
          startShimmer()
        } else {
          stopShimmer()
          isVisible = false
          motionLayout.isVisible = true
        }
      }
    }
  }
  //endregion

  //region listen to view model
  override fun observeViewModel() {
    animeJob?.cancel()
    animeJob = lifecycleScope.launch {
      animeViewModel.animeList.collectLatest { data ->
        animePagedAdapter?.submitData(data)
      }
    }
    animeViewModel.motionLayoutState.collectFlow { progressState ->
      this.binding.motionLayout.progress = progressState
    }
  }
  //endregion

  //region lifecycle
  override fun onPause() {
    super.onPause()
    animeViewModel.saveMotionLayoutState(binding.motionLayout.progress)
  }
  //endregion
}