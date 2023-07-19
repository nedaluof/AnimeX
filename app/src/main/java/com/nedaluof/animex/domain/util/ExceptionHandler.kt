package com.nedaluof.animex.domain.util

import kotlinx.coroutines.CancellationException
import okio.IOException
import retrofit2.HttpException
import java.util.concurrent.TimeoutException

/**
 * Created By NedaluOf - 7/15/2023.
 */

fun getRightException(
  exception: Exception
): Throwable {
  val exceptionMessage = when(exception){
    is IOException -> "IOException -> ${exception.message}"
    is HttpException -> "HttpException -> ${exception.message}"
    is TimeoutException -> "TimeoutException -> ${exception.message}"
    is CancellationException -> "CancellationException -> ${exception.message}"
    is ArrayIndexOutOfBoundsException -> "ArrayIndexOutOfBoundsException -> ${exception.message}"
    else -> "Exception -> ${exception.message}"
  }
  return Exception(exceptionMessage)
}