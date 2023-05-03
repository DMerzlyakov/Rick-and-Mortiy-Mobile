package com.example.rickandmorty.episode.domain.detail


import com.example.rickandmorty.episode.domain.detail.model.EpisodeDetailDomain
import io.reactivex.Single

interface GetEpisodeDetailUseCase {
    operator fun invoke(mId: Int): Single<EpisodeDetailDomain>
}