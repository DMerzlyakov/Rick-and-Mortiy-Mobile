package com.example.rickandmorty.episode.presentation.detail.mapper

import com.example.rickandmorty.episode.domain.detail.model.EpisodeDetailDomain
import com.example.rickandmorty.episode.presentation.detail.model.EpisodeDetailUi
import javax.inject.Inject

class EpisodeDetailDomainToEpisodeDetailUiMapper @Inject constructor() {
    operator fun invoke(item: EpisodeDetailDomain): EpisodeDetailUi {
        return EpisodeDetailUi(
            item.id,
            item.name,
            item.airDate,
            item.episode,
            item.characters
        )
    }
}
