package com.example.rickandmorty.episode.presentation.list.recyclerView

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemEpisodeBinding
import com.example.rickandmorty.episode.presentation.list.model.EpisodeUi
import com.example.rickandmorty.utils.OnClickRecyclerViewInterface

class EpisodesViewHolder(
    private val binding: ItemEpisodeBinding,
    onClickRecyclerViewInterface: OnClickRecyclerViewInterface<EpisodeUi>
) : RecyclerView.ViewHolder(binding.root) {

    var mItem: EpisodeUi? = null

    init {
        binding.root.setOnClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                mItem?.let { onClickRecyclerViewInterface.onItemClick(it, position) }
            }
        }
    }

    fun onBind(item: EpisodeUi) {
        with(binding) {
            nameView.text = item.name
            episodeNumberView.text = item.episode
            airDateView.text = item.airDate
        }

        mItem = item

    }

}