package com.example.rickandmorty.episode.data.detail

import com.example.rickandmorty.episode.data.detail.mapper.toEpisodeDetailDomain
import com.example.rickandmorty.episode.data.detail.remote.EpisodeDetailApi
import com.example.rickandmorty.episode.data.list.local.EpisodeDao
import com.example.rickandmorty.episode.domain.detail.EpisodeDetailRepository
import com.example.rickandmorty.episode.domain.detail.model.EpisodeDetailDomain
import io.reactivex.Single
import javax.inject.Inject

class EpisodeDetailRepositoryImpl @Inject constructor(
    private val episodeDetailApi: EpisodeDetailApi,
    private val episodeDao: EpisodeDao
) : EpisodeDetailRepository {


    private fun getEpisodeDetailByRemote(mId: Int) =
        episodeDetailApi.getEpisodeDetail(mId).map { it.toEpisodeDetailDomain() }

    override fun getEpisodeDetail(mId: Int): Single<EpisodeDetailDomain> {
        return getEpisodeDetailByRemote(mId).onErrorResumeNext { _: Throwable ->
            episodeDao.getEpisodeById(mId).map {
                it.toEpisodeDetailDomain()
            }
        }
    }
}