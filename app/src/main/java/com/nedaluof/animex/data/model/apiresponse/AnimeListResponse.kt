package com.nedaluof.animex.data.model.apiresponse

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created By NedaluOf - 7/7/2023.
 */
@JsonClass(generateAdapter = true)
data class AnimeListResponse(
  val pagination: Pagination,
  val data: List<AnimeData>
)

@JsonClass(generateAdapter = true)
data class AnimeData(
  @Json(name = "mal_id")
  val animeId: Long,
  val url: String?,
  val images: Map<String, Image>,
  val trailer: Trailer,
  val approved: Boolean?,
  val titles: List<Title>,
  val title: String?,
  @Json(name = "title_english")
  val titleEnglish: String? = null,
  @Json(name = "title_japanese")
  val titleJapanese: String? = null,
  @Json(name = "title_synonyms")
  val titleSynonyms: List<String>?,
  val type: String?,
  val source: String?,
  val episodes: Long?,
  val status: String?,
  val airing: Boolean?,
  val aired: Aired?,
  val duration: String?,
  val rating: String?,
  val score: Double? = null,
  @Json(name = "scored_by")
  val scoredBy: Long? = null,
  val rank: Long?,
  val popularity: Long?,
  val members: Long?,
  val favorites: Long?,
  val synopsis: String?,
  val background: String? = null,
  val season: String? = null,
  val year: Int? = null,
  val broadcast: Broadcast?,
  val producers: List<Genre?>?,
  val licensors: List<Genre?>?,
  val studios: List<Genre?>?,
  val genres: List<Genre?>?,
  val themes: List<Genre?>?,
  @Json(ignore = true)
  var page : Int = 0
)

@JsonClass(generateAdapter = true)
data class Aired(
  val from: String?,
  val to: String? = null,
  val prop: Prop?,
  val string: String?
)

@JsonClass(generateAdapter = true)
data class Prop(
  val from: From?,
  val to: From?
)

@JsonClass(generateAdapter = true)
data class From(
  val day: Long? = null,
  val month: Long? = null,
  val year: Long? = null
)

@JsonClass(generateAdapter = true)
data class Broadcast(
  val day: String? = null,
  val time: String? = null,
  val timezone: String? = null,
  val string: String? = null
)

@JsonClass(generateAdapter = true)
data class Genre(
  @Json(name = "mal_id")
  val malID: Long?,
  val type: String?,
  val name: String?,
  val url: String?
)

@JsonClass(generateAdapter = true)
data class Image(
  @Json(name = "image_url")
  val imageURL: String?,
  @Json(name = "small_image_url")
  val smallImageURL: String?,
  @Json(name = "large_image_url")
  val largeImageURL: String?
)

@JsonClass(generateAdapter = true)
data class Title(
  val type: String?,
  val title: String?
)

@JsonClass(generateAdapter = true)
data class Trailer(
  @Json(name = "youtube_id")
  val youtubeID: String? = null,
  val url: String? = null,
  @Json(name = "embed_url")
  val embedURL: String? = null,
  val images: Images
)

@JsonClass(generateAdapter = true)
data class Images(
  @Json(name = "image_url")
  val imageURL: String? = null,
  @Json(name = "small_image_url")
  val smallImageURL: String? = null,
  @Json(name = "medium_image_url")
  val mediumImageURL: String? = null,
  @Json(name = "large_image_url")
  val largeImageURL: String? = null,
  @Json(name = "maximum_image_url")
  val maximumImageURL: String? = null
)

@JsonClass(generateAdapter = true)
data class Pagination(
  @Json(name = "last_visible_page")
  val lastVisiblePage: Long?,
  @Json(name = "has_next_page")
  val hasNextPage: Boolean?,
  @Json(name = "current_page")
  val currentPage: Long?,
  val items: Items?
)

@JsonClass(generateAdapter = true)
data class Items(
  val count: Long?,
  val total: Long?,
  @Json(name = "per_page")
  val perPage: Long?
)