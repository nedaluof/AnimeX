package com.nedaluof.animex.utils

import android.view.View

/**
 * Created By NedaluOf - 7/8/2023.
 */

fun View.click(
  block : () -> Unit
) {
  setOnClickListener { block() }
}