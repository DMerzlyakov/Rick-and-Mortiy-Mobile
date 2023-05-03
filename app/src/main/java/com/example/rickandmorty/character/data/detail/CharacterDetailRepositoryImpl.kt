package com.example.rickandmorty.character.data.detail

import android.util.Log
import com.example.rickandmorty.character.data.detail.mapper.toCharacterEntity
import com.example.rickandmorty.character.data.detail.remote.CharacterDetailApi
import com.example.rickandmorty.character.data.list.local.CharacterListDao
import com.example.rickandmorty.character.domain.detail.CharacterDetailRepository
import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain
import com.example.rickandmorty.character.domain.detail.model.LocationDetailDomain
import io.reactivex.Observable
import javax.inject.Inject

class CharacterDetailRepositoryImpl @Inject constructor(
    private val characterDetailApi: CharacterDetailApi,
    private val CharacterDao: CharacterListDao
) : CharacterDetailRepository {



    private fun getCharacterDetailByRemote(mId: Int) =
        characterDetailApi.getDetailCharacter(mId).map { it.toCharacterEntity() }

    override fun getCharacterDetail(mId: Int): Observable<CharacterDetailDomain> {
        return getCharacterDetailByRemote(mId).flatMap {

                Observable.just(
                    CharacterDetailDomain(
                        it.id,
                        it.name,
                        it.status,
                        it.species,
                        it.gender,
                        LocationDetailDomain(it.originName, it.originId),
                        it.urlAvatar,
                        LocationDetailDomain(it.locationName, it.locationId),
                        it.episodes
                    )
                )

        }.onErrorResumeNext { _: Throwable ->
            CharacterDao.getCharacterById(mId).map {
                CharacterDetailDomain(
                    it.id,
                    it.name,
                    it.status,
                    it.species,
                    it.gender,
                    LocationDetailDomain(it.originName, it.originId),
                    it.urlAvatar,
                    LocationDetailDomain(it.locationName, it.locationId),
                    it.episodes
                )
            }

        }
    }
}