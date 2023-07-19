package com.nedaluof.animex.data.model.apiresponse

import androidx.room.TypeConverter
import com.nedaluof.animex.data.datasource.local.RoomTypeConverter
import com.nedaluof.animex.domain.model.anime.Anime
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

/**
 * Created By NedaluOf - 7/14/2023.
 */


class AnimeDataConverter {
  private val moshi: Moshi = Moshi.Builder().build()

  @TypeConverter
  fun toString(data: AnimeData): String {
    val adapter: JsonAdapter<AnimeData> = moshi.adapter(AnimeData::class.java)
    return adapter.toJson(data)
  }

  @TypeConverter
  fun fromString(data: String): AnimeData? {
    val adapter: JsonAdapter<AnimeData> = moshi.adapter(AnimeData::class.java)
    return adapter.fromJson(data)
  }
}