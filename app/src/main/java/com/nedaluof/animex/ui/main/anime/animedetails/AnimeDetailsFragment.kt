package com.nedaluof.animex.ui.main.anime.animedetails

import android.widget.RelativeLayout
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.nedaluof.animex.R
import com.nedaluof.animex.databinding.FragmentAnimeDetailsBinding
import com.nedaluof.animex.ui.base.BaseFragment
import com.nedaluof.animex.utils.click
import com.nedaluof.animex.utils.dp
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit


/**
 * Created By NedaluOf - 7/8/2023.
 */
@AndroidEntryPoint
class AnimeDetailsFragment :
  BaseFragment<FragmentAnimeDetailsBinding>(R.layout.fragment_anime_details) {

  //region variables
  private val args: AnimeDetailsFragmentArgs by navArgs()
  //end region

  //region view related
  override fun onBindingReady() {
    ViewCompat.setTransitionName(binding.animeImage, args.anime?.posterImage)

    postponeEnterTransition(200, TimeUnit.MILLISECONDS)
  }

  override fun initViews() {
    with(binding) {
      args.anime?.let { animeModel ->
        anime = animeModel
        animeImage.post {
          val layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT, 550.dp
          )
          animeImage.layoutParams = layoutParams
          animeImage.requestLayout()
        }
      }
    }
  }

  override fun initClicks() {
    binding.backButton.click {
      findNavController().popBackStack()
    }
  }
  //end region
}