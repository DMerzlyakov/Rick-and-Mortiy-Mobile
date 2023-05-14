package com.example.rickandmorty.episode.presentation.list.mapper

import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import com.example.rickandmorty.episode.presentation.list.model.EpisodeUi
import javax.inject.Inject


class EpisodeDomainToEpisodeUiMapper @Inject constructor(){
    operator fun invoke(item: EpisodeDomain): EpisodeUi {
        return EpisodeUi(
            item.id,
            item.name,
            item.airDate,
            item.episode
        )
    }
}
