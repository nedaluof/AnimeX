package com.nedaluof.animex.data.model.apiresponse

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.rawType
import java.lang.reflect.Type
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable
import kotlin.reflect.jvm.internal.impl.types.KotlinType
import kotlin.reflect.typeOf

/**
 * Created By NedaluOf - 7/7/2023.
 */

@JsonClass(generateAdapter = true)
data class AnimeListResponse(
  val data: List<AnimeData>,
)

@Entity(tableName = "anime_table")
@JsonClass(generateAdapter = true)
data class AnimeData(
  @PrimaryKey(autoGenerate = false)
  val id: String,
  val type: String,
  val links: Links?,
  val attributes: Attributes,
  val relationships: Relationships,
  @Json(ignore = true)
  var pageOffset : Int = 0
)

@JsonClass(generateAdapter = true)
data class Links(
  val self: String,
)

@JsonClass(generateAdapter = true)
data class Attributes(
  val createdAt: String?,
  val updatedAt: String?,
  val slug: String?,
  val synopsis: String?,
  val description: String?,
  val coverImageTopOffset: Long?,
  val titles: Titles?,
  val canonicalTitle: String?,
  val abbreviatedTitles: List<String?>?,
  val averageRating: String?,
  val ratingFrequencies: RatingFrequencies?,
  val userCount: Long?,
  val favoritesCount: Long?,
  val startDate: String?,
  val endDate: String?,
  val nextRelease: Any?,
  val popularityRank: Long?,
  val ratingRank: Long?,
  val ageRating: String?,
  val ageRatingGuide: String?,
  val subtype: String?,
  val status: String?,
  val tba: String?,
  val posterImage: AnimeImage?,
  val coverImage: AnimeImage?,
  val episodeCount: Long?,
  val episodeLength: Long?,
  val totalLength: Long?,
  val youtubeVideoId: String?,
  val showType: String?,
  val nsfw: Boolean?,
)

@JsonClass(generateAdapter = true)
data class Titles(
  val en: String?,
  @Json(name = "en_jp")
  val enJp: String?,
  @Json(name = "en_us")
  val enUs: String?,
  @Json(name = "ja_jp")
  val jaJp: String?,
)

@JsonClass(generateAdapter = true)
data class RatingFrequencies(
  @Json(name = "2")
  val n2: String?,
  @Json(name = "3")
  val n3: String?,
  @Json(name = "4")
  val n4: String?,
  @Json(name = "5")
  val n5: String?,
  @Json(name = "6")
  val n6: String?,
  @Json(name = "7")
  val n7: String?,
  @Json(name = "8")
  val n8: String?,
  @Json(name = "9")
  val n9: String?,
  @Json(name = "10")
  val n10: String?,
  @Json(name = "11")
  val n11: String?,
  @Json(name = "12")
  val n12: String?,
  @Json(name = "13")
  val n13: String?,
  @Json(name = "14")
  val n14: String?,
  @Json(name = "15")
  val n15: String?,
  @Json(name = "16")
  val n16: String?,
  @Json(name = "17")
  val n17: String?,
  @Json(name = "18")
  val n18: String?,
  @Json(name = "19")
  val n19: String?,
  @Json(name = "20")
  val n20: String?,
)

@JsonClass(generateAdapter = true)
data class AnimeImage(
  val tiny: String?,
  val large: String?,
  val small: String?,
  val medium: String?,
  val original: String?,
  val meta: Meta?,
)

@JsonClass(generateAdapter = true)
data class Meta(
  val dimensions: Dimensions?,
)

@JsonClass(generateAdapter = true)
data class Dimensions(
  val tiny: ImageDimension?,
  val large: ImageDimension?,
  val small: ImageDimension?,
  val medium: ImageDimension?,
  val original: ImageDimension?
)

@JsonClass(generateAdapter = true)
data class ImageDimension(
  val width: Long?,
  val height: Long?,
)

@JsonClass(generateAdapter = true)
data class Relationships(
  val genres: Relationship?,
  val categories: Relationship?,
  val castings: Relationship?,
  val installments: Relationship?,
  val mappings: Relationship?,
  val reviews: Relationship?,
  val mediaRelationships: Relationship?,
  val characters: Relationship?,
  val staff: Relationship?,
  val productions: Relationship?,
  val quotes: Relationship?,
  val episodes: Relationship?,
  val streamingLinks: Relationship?,
  val animeProductions: Relationship?,
  val animeCharacters: Relationship?,
  val animeStaff: Relationship?,
)

@JsonClass(generateAdapter = true)
data class Relationship(
  val links: RelationshipData?,
)

@JsonClass(generateAdapter = true)
data class RelationshipData(
  val self: String?,
  val related: String?,
)