package com.nedaluof.animex.domain.model.anime

import com.nedaluof.animex.data.model.apiresponse.AnimeData
import com.nedaluof.animex.data.model.apiresponse.Title
import com.nedaluof.animex.domain.model.common.ModelMapper
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/8/2023.
 */
class AnimeMapper @Inject constructor() : ModelMapper<AnimeData, Anime> {

  override fun fromModel(inputModel: AnimeData): Anime = with(inputModel) {
    val anime = Anime()
    anime.id = animeId
    anime.englishName = titleEnglish ?: getTitle(titles, "English") ?: ""
    anime.japaneseName = titleJapanese ?: getTitle(titles, "Japanese")  ?: ""
    anime.description = synopsis ?: ""
    if (images.isNotEmpty()) {
      val imageUrls = images.values.first()
      anime.posterImage =
        imageUrls.largeImageURL ?: (imageUrls.imageURL ?: (imageUrls.smallImageURL ?: ""))
    } else {
      trailer.images.let { trailerImages ->
        trailerImages.let { imagesT ->
          anime.posterImage = imagesT.largeImageURL ?: (imagesT.imageURL ?: (imagesT.smallImageURL
            ?: (imagesT.maximumImageURL ?: (imagesT.mediumImageURL ?: ""))))
        }
      }
    }
    anime.startDate = aired?.from?.substringBefore("T") ?: "ـــ"
    anime.endDate = aired?.to?.substringBefore("T") ?: "ـــ"
    anime.showType = type ?: ""
    anime.status = status ?: ""
    anime.averageRating = "0.0"
    score?.let {
      val decimalFormat = DecimalFormat("#.##").apply {
        roundingMode = RoundingMode.DOWN
      }
      val cleanScore = decimalFormat.format((it * 10.0))
      anime.averageRating = cleanScore.toString()
    }
    anime.usersCount = popularity.toString()
    anime.favoritesCount = favorites.toString()
    anime.episodesCount = (episodes ?: 0).toString()
    anime.episodesLength = duration ?: "25 min"
    anime
  }

  private fun getTitle(
    titles: List<Title>,
    pattern: String
  ) = if (titles.isNotEmpty()) {
    val title = titles.firstOrNull { it.type == pattern }
    title?.title ?: titles[0].title
  } else {
    ""
  }
}