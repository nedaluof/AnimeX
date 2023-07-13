package com.nedaluof.animex.utils

import android.content.res.Resources

/**
 * Created By NedaluOf - 7/12/2023.
 */
val Int.dp: Int
  get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()