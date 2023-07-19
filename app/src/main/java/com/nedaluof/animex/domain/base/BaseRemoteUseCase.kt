package com.nedaluof.animex.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Response

/**
 * Created By NedaluOf - 7/7/2023.
 */
open class BaseRemoteUseCase {
  fun <RESPONSE_MODEL> requestApi(
    scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO),
    api: suspend () -> Response<RESPONSE_MODEL>,
    onLoading: (Boolean) -> Unit = {},
    onSuccess: (RESPONSE_MODEL) -> Unit = {},
    onError: (String) -> Unit = {}
  ) {
    scope.launch {
      onLoading(true)
      try {
        val response = api()
        if (response.isSuccessful) {
          onSuccess(response.body()!!)
        } else {
          onError(response.message())
        }
      } catch (e: Exception) {
        e.printStackTrace()
        e.localizedMessage?.let { onError(it) }
      }
      onLoading(false)
    }
  }
}