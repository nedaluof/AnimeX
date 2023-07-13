package com.nedaluof.animex.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Created By NedaluOf - 7/7/2023.
 */
abstract class BaseActivity<DB : ViewDataBinding>(
  private val layoutId: Int
) : AppCompatActivity() {

  private var _binding: DB? = null
  protected val binding: DB
    get() = _binding!!

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    _binding = DataBindingUtil.setContentView(this, layoutId)
    initViews()
    initClicks()
    doLogic()
    observeViewModel()
    loadData()
  }

  fun <T> StateFlow<T>.collectFlow(data: (T) -> Unit) {
    lifecycleScope.launch {
      collect { data(it) }
    }
  }

  open fun initViews() {
    /**
     * used by child classes
     * */
  }

  open fun initClicks() {
    /**
     * used by child classes
     * */
  }

  open fun doLogic() {
    /**
     * used by child classes
     * */
  }

  open fun loadData() {
    /**
     * used by child classes
     * */
  }

  open fun observeViewModel() {
    /**
     * used by child classes
     * */
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }
}