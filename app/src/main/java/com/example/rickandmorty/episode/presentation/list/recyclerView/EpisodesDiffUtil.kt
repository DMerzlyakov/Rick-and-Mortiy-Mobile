package com.example.rickandmorty.episode.presentation.list.recyclerView

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.episode.presentation.list.model.EpisodeUi

class EpisodesDiffUtil : DiffUtil.ItemCallback<EpisodeUi>() {
    override fun areItemsTheSame(oldItem: EpisodeUi, newItem: EpisodeUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EpisodeUi, newItem: EpisodeUi): Boolean {
        return oldItem == newItem
    }
}