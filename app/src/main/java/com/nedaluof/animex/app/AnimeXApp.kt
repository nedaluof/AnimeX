package com.nedaluof.animex.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created By NedaluOf - 7/7/2023.
 */
@HiltAndroidApp
class AnimeXApp : Application() {
  override fun onCreate() {
    super.onCreate()
    Timber.plant(Timber.DebugTree())
  }
}