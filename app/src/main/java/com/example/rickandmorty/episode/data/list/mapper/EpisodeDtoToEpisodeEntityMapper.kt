package com.example.rickandmorty.episode.data.list.mapper

import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import com.example.rickandmorty.episode.data.list.remote.model.EpisodeDto
import javax.inject.Inject

class EpisodeDtoToEpisodeEntityMapper @Inject constructor() {

    operator fun invoke(item: EpisodeDto): List<EpisodeEntity> {
        return item.results.map {

            var characterIdList = emptyList<Int>()
            if (it.characters.isNotEmpty()) {
                characterIdList = it.characters.map { item -> item.split("/").last().toInt() }
            }

            EpisodeEntity(it.id, it.name, it.air_date, it.episode, characterIdList)
        }
    }
}