package com.nedaluof.animex.domain.model.anime

import com.nedaluof.animex.data.datasource.remote.apiresponse.AnimeImage
import com.nedaluof.animex.data.datasource.remote.apiresponse.Data
import com.nedaluof.animex.domain.model.common.ModelMapper
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/8/2023.
 */
class AnimeMapper @Inject constructor() : ModelMapper<Data, Anime> {

  override fun fromModel(inputModel: Data): Anime = with(inputModel) {
    val anime = Anime()
    anime.id = id
    with(attributes) {
      anime.englishName =
        titles?.en ?: (titles?.enUs ?: (titles?.enJp ?: ""))
      anime.japaneseName = titles?.jaJp ?: (titles?.enJp ?: "")
      anime.description = description ?: ""
      posterImage?.let {
        val imageData = getAnimeImageData(it)
        anime.posterImage = imageData.first
        anime.posterDimension = imageData.second
      }
      coverImage?.let {
        val imageData = getAnimeImageData(it)
        anime.coverImage = imageData.first
        anime.coverDimension = imageData.second
      }
      anime.startDate = startDate ?: ""
      anime.endDate = endDate ?: ""
      anime.showType = showType ?: ""
      anime.status = status ?: ""
      anime.averageRating = averageRating ?: ""
      anime.usersCount = userCount.toString()
      anime.favoritesCount = favoritesCount.toString()
      anime.episodesCount = episodeCount.toString()
      anime.episodesLength = episodeLength.toString()
    }
    anime
  }

  private fun getAnimeImageData(
    imageData: AnimeImage?
  ): Pair<String, AnimeImageDimension> {
    var availableImageType = 1
    var image = ""
    var height = 0L
    var width = 0L
    imageData?.let {
      if (!it.large.isNullOrEmpty()) {
        availableImageType = 1
        image = it.large
      } else if (!it.medium.isNullOrEmpty()) {
        availableImageType = 2
        image = it.medium
      } else if (!it.original.isNullOrEmpty()) {
        availableImageType = 3
        image = it.original
      } else if (!it.tiny.isNullOrEmpty()) {
        availableImageType = 4
        image = it.tiny
      }
    }

    imageData?.meta?.dimensions?.let {
      when (availableImageType) {
        1 -> {
          it.large?.let { dimension ->
            height = dimension.height ?: 0
            width = dimension.width ?: 0
          }
        }

        2 -> {
          it.medium?.let { dimension ->
            height = dimension.height ?: 0
            width = dimension.width ?: 0
          }
        }

        3 -> {
          it.original?.let { dimension ->
            height = dimension.height ?: 0
            width = dimension.width ?: 0
          }
        }

        else -> {
          it.tiny?.let { dimension ->
            height = dimension.height ?: 0
            width = dimension.width ?: 0
          }
        }
      }
    }
    return Pair(image, AnimeImageDimension(height, width))
  }
}