package com.example.rickandmorty.episode.data.detail.mapper

import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import com.example.rickandmorty.episode.domain.detail.model.EpisodeDetailDomain
import javax.inject.Inject


class EpisodeEntityToEpisodeDetailDomain @Inject constructor() {

    operator fun invoke(item: EpisodeEntity): EpisodeDetailDomain {

        return EpisodeDetailDomain(
            item.id,
            item.name,
            item.airDate,
            item.episode,
            item.characters
        )
    }
}