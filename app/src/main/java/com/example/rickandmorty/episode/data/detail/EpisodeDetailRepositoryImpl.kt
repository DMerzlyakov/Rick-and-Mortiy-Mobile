package com.example.rickandmorty.episode.data.detail

import com.example.rickandmorty.character.data.detail.mapper.CharacterDetailDtoToCharacterEntityMapper
import com.example.rickandmorty.episode.data.detail.mapper.EpisodeDetailDtoToEpisodeEntityMapper
import com.example.rickandmorty.episode.data.detail.mapper.EpisodeEntityToEpisodeDetailDomain
import com.example.rickandmorty.episode.data.detail.remote.EpisodeDetailApi
import com.example.rickandmorty.episode.data.list.local.EpisodeDao
import com.example.rickandmorty.episode.domain.detail.EpisodeDetailRepository
import com.example.rickandmorty.episode.domain.detail.model.EpisodeDetailDomain
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class EpisodeDetailRepositoryImpl @Inject constructor(
    private val episodeDetailApi: EpisodeDetailApi,
    private val episodeDao: EpisodeDao,
    private val dtoToEntityMapper: EpisodeDetailDtoToEpisodeEntityMapper,
    private val entityToDomainMapper: EpisodeEntityToEpisodeDetailDomain
) : EpisodeDetailRepository {


    private fun getEpisodeDetailByRemote(mId: Int) =
        episodeDetailApi.getEpisodeDetail(mId).map { dtoToEntityMapper(it) }

    override fun getEpisodeDetail(mId: Int): Single<EpisodeDetailDomain> {
        return getEpisodeDetailByRemote(mId).flatMap { item ->
            episodeDao.saveEpisode(item).andThen(
                Single.just(entityToDomainMapper(item))
            )
        }.onErrorResumeNext { _: Throwable ->
            episodeDao.getEpisodeById(mId).map {
                entityToDomainMapper(it)
            }
        }
    }
}