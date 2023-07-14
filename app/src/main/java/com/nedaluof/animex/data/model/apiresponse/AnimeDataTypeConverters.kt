package com.nedaluof.animex.data.model.apiresponse

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.nedaluof.animex.data.datasource.local.RoomTypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

/**
 * Created By NedaluOf - 7/14/2023.
 */

@ProvidedTypeConverter
class LinksTypeConverter(
  private val moshi: Moshi
) : RoomTypeConverter<Links> {
  @TypeConverter
  override fun toString(data: Links): String {
    val adapter: JsonAdapter<Links> = moshi.adapter(Links::class.java)
    return adapter.toJson(data)
  }

  @TypeConverter
  override fun fromString(data: String): Links? {
    val adapter: JsonAdapter<Links> = moshi.adapter(Links::class.java)
    return adapter.fromJson(data)
  }
}

@ProvidedTypeConverter
class AttributesTypeConverter(
  private val moshi: Moshi
) : RoomTypeConverter<Attributes> {
  @TypeConverter
  override fun toString(data: Attributes): String {
    val adapter: JsonAdapter<Attributes> = moshi.adapter(Attributes::class.java)
    return adapter.toJson(data)
  }

  @TypeConverter
  override fun fromString(data: String): Attributes? {
    val adapter: JsonAdapter<Attributes> = moshi.adapter(Attributes::class.java)
    return adapter.fromJson(data)
  }
}

@ProvidedTypeConverter
class RelationshipsTypeConverter(
  private val moshi: Moshi
) : RoomTypeConverter<Relationships> {
  @TypeConverter
  override fun toString(data: Relationships): String {
    val adapter: JsonAdapter<Relationships> = moshi.adapter(Relationships::class.java)
    return adapter.toJson(data)
  }

  @TypeConverter
  override fun fromString(data: String): Relationships? {
    val adapter: JsonAdapter<Relationships> = moshi.adapter(Relationships::class.java)
    return adapter.fromJson(data)
  }
}

@ProvidedTypeConverter
class RelationshipTypeConverter(
  private val moshi: Moshi
) : RoomTypeConverter<Relationship> {
  @TypeConverter
  override fun toString(data: Relationship): String {
    val adapter: JsonAdapter<Relationship> = moshi.adapter(Relationship::class.java)
    return adapter.toJson(data)
  }

  @TypeConverter
  override fun fromString(data: String): Relationship? {
    val adapter: JsonAdapter<Relationship> = moshi.adapter(Relationship::class.java)
    return adapter.fromJson(data)
  }
}

@ProvidedTypeConverter
class RelationshipDataTypeConverter(
  private val moshi: Moshi
) : RoomTypeConverter<RelationshipData> {
  @TypeConverter
  override fun toString(data: RelationshipData): String {
    val adapter: JsonAdapter<RelationshipData> = moshi.adapter(RelationshipData::class.java)
    return adapter.toJson(data)
  }

  @TypeConverter
  override fun fromString(data: String): RelationshipData? {
    val adapter: JsonAdapter<RelationshipData> = moshi.adapter(RelationshipData::class.java)
    return adapter.fromJson(data)
  }
}

@ProvidedTypeConverter
class ImageDimensionTypeConverter(
  private val moshi: Moshi
) : RoomTypeConverter<ImageDimension> {
  @TypeConverter
  override fun toString(data: ImageDimension): String {
    val adapter: JsonAdapter<ImageDimension> = moshi.adapter(ImageDimension::class.java)
    return adapter.toJson(data)
  }

  @TypeConverter
  override fun fromString(data: String): ImageDimension? {
    val adapter: JsonAdapter<ImageDimension> = moshi.adapter(ImageDimension::class.java)
    return adapter.fromJson(data)
  }
}

@ProvidedTypeConverter
class DimensionsTypeConverter(
  private val moshi: Moshi
) : RoomTypeConverter<Dimensions> {
  @TypeConverter
  override fun toString(data: Dimensions): String {
    val adapter: JsonAdapter<Dimensions> = moshi.adapter(Dimensions::class.java)
    return adapter.toJson(data)
  }

  @TypeConverter
  override fun fromString(data: String): Dimensions? {
    val adapter: JsonAdapter<Dimensions> = moshi.adapter(Dimensions::class.java)
    return adapter.fromJson(data)
  }
}

@ProvidedTypeConverter
class MetaTypeConverter(
  private val moshi: Moshi
) : RoomTypeConverter<Meta> {
  @TypeConverter
  override fun toString(data: Meta): String {
    val adapter: JsonAdapter<Meta> = moshi.adapter(Meta::class.java)
    return adapter.toJson(data)
  }

  @TypeConverter
  override fun fromString(data: String): Meta? {
    val adapter: JsonAdapter<Meta> = moshi.adapter(Meta::class.java)
    return adapter.fromJson(data)
  }
}

@ProvidedTypeConverter
class AnimeImageTypeConverter(
  private val moshi: Moshi
) : RoomTypeConverter<AnimeImage> {
  @TypeConverter
  override fun toString(data: AnimeImage): String {
    val adapter: JsonAdapter<AnimeImage> = moshi.adapter(AnimeImage::class.java)
    return adapter.toJson(data)
  }

  @TypeConverter
  override fun fromString(data: String): AnimeImage? {
    val adapter: JsonAdapter<AnimeImage> = moshi.adapter(AnimeImage::class.java)
    return adapter.fromJson(data)
  }
}

@ProvidedTypeConverter
class RatingFrequenciesTypeConverter(
  private val moshi: Moshi
) : RoomTypeConverter<RatingFrequencies> {
  @TypeConverter
  override fun toString(data: RatingFrequencies): String {
    val adapter: JsonAdapter<RatingFrequencies> = moshi.adapter(RatingFrequencies::class.java)
    return adapter.toJson(data)
  }

  @TypeConverter
  override fun fromString(data: String): RatingFrequencies? {
    val adapter: JsonAdapter<RatingFrequencies> = moshi.adapter(RatingFrequencies::class.java)
    return adapter.fromJson(data)
  }
}

@ProvidedTypeConverter
class TitlesTypeConverter(
  private val moshi: Moshi
) : RoomTypeConverter<Titles> {
  @TypeConverter
  override fun toString(data: Titles): String {
    val adapter: JsonAdapter<Titles> = moshi.adapter(Titles::class.java)
    return adapter.toJson(data)
  }

  @TypeConverter
  override fun fromString(data: String): Titles? {
    val adapter: JsonAdapter<Titles> = moshi.adapter(Titles::class.java)
    return adapter.fromJson(data)
  }
}