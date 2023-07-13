package com.nedaluof.animex.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.transition.TransitionInflater
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Created By NedaluOf - 7/7/2023.
 */
abstract class BaseFragment<DB : ViewDataBinding>(
  private val layoutId: Int
) : Fragment() {

  private var _binding: DB? = null
  val binding: DB
    get() = _binding!!

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initTransition()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
    onBindingReady()
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initViews()
    initClicks()
    doLogic()
    observeViewModel()
    loadData()
  }

  private fun initTransition(){
    val animation =
      TransitionInflater.from(requireActivity()).inflateTransition(android.R.transition.move)
    sharedElementEnterTransition = animation
  }

  open fun onBindingReady() {
    /**
     * used by child classes
     * */
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

  fun <T> StateFlow<T>.collectFlow(block: (T) -> Unit) {
    lifecycleScope.launch {
      this@collectFlow.collect { block(it) }
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }
}