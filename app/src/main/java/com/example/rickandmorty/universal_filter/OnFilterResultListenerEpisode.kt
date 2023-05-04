package com.example.rickandmorty.universal_filter

import com.example.rickandmorty.episode.domain.list.model.EpisodeFilter

interface OnFilterResultListenerEpisode {
    fun confirmFilter(item: EpisodeFilter? = null)

}