package com.example.rickandmorty.episode.data.detail.mapper

import com.example.rickandmorty.episode.data.detail.remote.model.EpisodeDetailDTO
import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import javax.inject.Inject

class EpisodeDetailDtoToEpisodeEntityMapper @Inject constructor() {

    operator fun invoke(item: EpisodeDetailDTO): EpisodeEntity {

        var characterIdList = emptyList<Int>()
        if (item.characters.isNotEmpty())
            characterIdList = item.characters.map {
                it.split("/").last().toInt()
            }

        return EpisodeEntity(
            item.id,
            item.name,
            item.air_date,
            item.episode,
            characterIdList
        )
    }
}
