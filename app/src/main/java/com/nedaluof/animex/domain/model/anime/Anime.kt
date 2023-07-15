package com.nedaluof.animex.domain.model.anime

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created By NedaluOf - 7/8/2023.
 */
@Parcelize
data class Anime(
  var id: String = "",
  var englishName: String = "",
  var japaneseName: String = "",
  var description: String = "",
  var posterImage: String = "",
  var posterDimension: AnimeImageDimension = AnimeImageDimension(),
  var coverImage: String = "",
  var coverDimension: AnimeImageDimension = AnimeImageDimension(),
  var startDate: String = "",
  var endDate: String = "",
  var showType: String = "",
  var status: String = "",
  var averageRating: String = "",
  var usersCount: String = "",
  var favoritesCount: String = "",
  var episodesCount: String = "",
  var episodesLength: String = "",
  var pageOffset: Int = 0
) : Parcelable {
  fun getAverageTitle(): String {
    return "Average Rating $averageRating"
  }
}

@Parcelize
data class AnimeImageDimension(
  var width: Long? = 0,
  var height: Long? = 0
) : Parcelable