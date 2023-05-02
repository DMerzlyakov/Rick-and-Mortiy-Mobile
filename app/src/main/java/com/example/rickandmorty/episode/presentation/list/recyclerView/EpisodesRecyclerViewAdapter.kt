package com.example.rickandmorty.episode.presentation.list.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.rickandmorty.databinding.ItemEpisodeBinding
import com.example.rickandmorty.episode.presentation.list.model.EpisodeUi
import com.example.rickandmorty.extention_util.OnClickRecyclerViewInterface

class EpisodesRecyclerViewAdapter(private val onClickRecyclerViewInterface: OnClickRecyclerViewInterface<EpisodeUi>) :
    PagingDataAdapter<EpisodeUi, EpisodesViewHolder>(EpisodesDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickRecyclerViewInterface
        )
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.onBind(getItem(position) ?: return)
    }
}