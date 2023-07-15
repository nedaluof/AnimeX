package com.nedaluof.animex.app

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created By NedaluOf - 7/7/2023.
 */
@HiltAndroidApp
class AnimeXApp : Application(), ImageLoaderFactory {
  override fun onCreate() {
    super.onCreate()
    Timber.plant(Timber.DebugTree())
  }

  override fun newImageLoader(): ImageLoader {
    return ImageLoader.Builder(this)
      .crossfade(true)
      .memoryCache {
        MemoryCache.Builder(this)
          .maxSizePercent(0.30)
          .build()
      }
      .diskCache {
        DiskCache.Builder()
          .directory(cacheDir.resolve("coil_cache"))
          .maxSizePercent(0.02)
          .build()
      }
      .build()
  }
}