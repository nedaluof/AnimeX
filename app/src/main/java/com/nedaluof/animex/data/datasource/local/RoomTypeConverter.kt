package com.nedaluof.animex.data.datasource.local

/**
 * Created By NedaluOf - 7/14/2023.
 */
interface RoomTypeConverter<T> {

  fun toString(data: T): String

  fun fromString(data: String): T?
}