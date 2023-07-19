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
class AnimeXApp : Application() {
  override fun onCreate() {
    super.onCreate()
    prepareImageLoaderCaching()
    Timber.plant(Timber.DebugTree())
  }

  private fun prepareImageLoaderCaching() = ImageLoaderFactory {
    ImageLoader.Builder(this)
      .memoryCache {
        MemoryCache.Builder(this)
          .maxSizePercent(0.25)
          .build()
      }
      .diskCache {
        DiskCache.Builder()
          .directory(cacheDir.resolve("anime_x_image_cache"))
          .maxSizeBytes(5 * 1024 * 1024)
          .build()
      }
      .build()
  }
}