package com.nedaluof.animex.domain.model.common

/**
 * Created By NedaluOf - 7/7/2023.
 */
sealed class Resource<T>(
  val data: T? = null, val loading: Boolean = false, val error: String? = null
) {
  class Success<T>(data: T? = null) : Resource<T>(data)
  class Loading<T>(loading: Boolean = false , data: T? = null) : Resource<T>(data = data ,loading = loading)
  class Error<T>(error: String? = null , data: T? = null) : Resource<T>(data = data ,error = error)
}