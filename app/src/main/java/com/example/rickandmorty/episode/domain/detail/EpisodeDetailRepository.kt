package com.example.rickandmorty.episode.domain.detail

import com.example.rickandmorty.episode.domain.detail.model.EpisodeDetailDomain
import io.reactivex.Single

interface EpisodeDetailRepository {

    fun getEpisodeDetail(mId: Int): Single<EpisodeDetailDomain>

}