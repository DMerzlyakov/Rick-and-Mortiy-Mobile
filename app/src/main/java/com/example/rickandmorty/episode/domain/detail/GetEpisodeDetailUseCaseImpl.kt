package com.example.rickandmorty.episode.domain.detail


import com.example.rickandmorty.episode.domain.detail.model.EpisodeDetailDomain
import io.reactivex.Single
import javax.inject.Inject

class GetEpisodeDetailUseCaseImpl @Inject constructor(
    private val episodeDetailRepository: EpisodeDetailRepository
) : GetEpisodeDetailUseCase {
    override fun invoke(mId: Int): Single<EpisodeDetailDomain> {
        return episodeDetailRepository.getEpisodeDetail(mId)
    }

}