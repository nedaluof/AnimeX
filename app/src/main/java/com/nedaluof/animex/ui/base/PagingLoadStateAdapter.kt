package com.nedaluof.animex.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nedaluof.animex.databinding.ItemStateBinding

/**
 * Created By NedaluOf - 7/7/2023.
 */
class PagingLoadStateAdapter(
  private val retry: () -> Unit,
) : LoadStateAdapter<PagingLoadStateAdapter.LoadStateVH>() {

  override fun onBindViewHolder(holder: LoadStateVH, loadState: LoadState) {
    holder.bind(loadState)
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    loadState: LoadState,
  ) = LoadStateVH(
    ItemStateBinding.inflate(
      LayoutInflater.from(parent.context),
      parent,
      false
    ), retry
  )

  class LoadStateVH(
    private val binding: ItemStateBinding,
    retry: () -> Unit,
  ) : RecyclerView.ViewHolder(binding.root) {

    init {
      binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
      with(binding) {
        progressBar.isVisible = loadState is LoadState.Loading
        retryButton.isVisible = loadState is LoadState.Error
        errorMsg.text = if (loadState is LoadState.Error) loadState.error.localizedMessage else ""
        errorMsg.isVisible = errorMsg.text.isNotEmpty()
      }
    }
  }
}