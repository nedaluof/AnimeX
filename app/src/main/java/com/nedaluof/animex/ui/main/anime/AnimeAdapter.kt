package com.nedaluof.animex.ui.main.anime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.nedaluof.animex.databinding.ItemAnimeBinding
import com.nedaluof.animex.domain.model.anime.Anime
import com.nedaluof.animex.utils.click

/**
 * Created By NedaluOf - 7/7/2023.
 */
class AnimeAdapter(
  private val onItemClicked: (Anime, Pair<View, String>) -> Unit
) : PagingDataAdapter<Anime, AnimeAdapter.AnimeVH>(QuoteDiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AnimeVH(
    ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
  )

  override fun onBindViewHolder(holder: AnimeVH, position: Int) {
    holder.bind(getItem(position)!!)
  }

  inner class AnimeVH(
    private val binding: ItemAnimeBinding
  ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(anime: Anime) {
      with(binding) {
        ViewCompat.setTransitionName(animeImage, anime.posterImage)
        this.anime = anime
        executePendingBindings()
        cardView.click {
          onItemClicked(
            anime,
              animeImage to anime.posterImage
          )
        }
      }
    }
  }

  private class QuoteDiffCallback : DiffUtil.ItemCallback<Anime>() {
    override fun areItemsTheSame(oldItem: Anime, newItem: Anime) =
      oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Anime, newItem: Anime) =
      oldItem == newItem
  }
}