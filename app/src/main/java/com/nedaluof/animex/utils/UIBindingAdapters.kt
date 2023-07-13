package com.nedaluof.animex.utils

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import coil.request.CachePolicy
import com.nedaluof.animex.R
import com.nedaluof.animex.ui.custom.LinearProgressBar

/**
 * Created By NedaluOf - 7/7/2023.
 */
object UIBindingAdapters {
  @BindingAdapter("ctrlVisibility")
  @JvmStatic
  fun View.controlVisibility(
    show: Boolean
  ) {
    isVisible = show
  }

  @BindingAdapter("loadUrl")
  @JvmStatic
  fun ImageView.loadImageUrl(
    url: String?
  ) {
    url?.let {
      val ctx = this.context
      this.load(it) {
        diskCachePolicy(CachePolicy.ENABLED)
        memoryCachePolicy(CachePolicy.ENABLED)
        networkCachePolicy(CachePolicy.ENABLED)
        placeholder(CircularProgressDrawable(ctx).apply {
          backgroundColor = ContextCompat.getColor(ctx, R.color.bright_light_green)
          strokeWidth = 5f
          centerRadius = 30f
          start()
        })
      }
    }
  }

  @BindingAdapter("setStrValue")
  @JvmStatic
  fun LinearProgressBar.setStrValue(
    value: String?
  ) {
    value?.let {
      this.stringPercent = it
    }
  }

  @BindingAdapter("setProgressTitle")
  @JvmStatic
  fun LinearProgressBar.setProgressTitle(
    progressTitle: String?
  ) {
    progressTitle?.let {
      this.progressTitle = it
    }
  }
}