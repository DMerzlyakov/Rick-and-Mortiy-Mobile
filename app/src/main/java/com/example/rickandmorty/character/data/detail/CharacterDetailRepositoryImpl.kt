package com.example.rickandmorty.character.data.detail

import com.example.rickandmorty.character.data.detail.mapper.CharacterDetailDtoToCharacterEntityMapper
import com.example.rickandmorty.character.data.detail.mapper.CharacterEntityToCharacterDetailDomainMapper
import com.example.rickandmorty.character.data.detail.remote.CharacterDetailApi
import com.example.rickandmorty.character.data.list.local.CharacterListDao
import com.example.rickandmorty.character.domain.detail.CharacterDetailRepository
import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain
import io.reactivex.Single
import javax.inject.Inject

class CharacterDetailRepositoryImpl @Inject constructor(
    private val characterDetailApi: CharacterDetailApi,
    private val characterDao: CharacterListDao,
    private val dtoToEntityMapper: CharacterDetailDtoToCharacterEntityMapper,
    private val entityToDomainMapper: CharacterEntityToCharacterDetailDomainMapper
) : CharacterDetailRepository {

    private fun getCharacterDetailByRemote(mId: Int) =
        characterDetailApi.getDetailCharacter(mId).map { dtoToEntityMapper(it) }

    override fun getCharacterDetail(mId: Int): Single<CharacterDetailDomain> {
        return getCharacterDetailByRemote(mId).flatMap { item ->
            characterDao.saveCharacter(item).andThen(
                Single.just(
                    entityToDomainMapper(item)
                )
            ).onErrorResumeNext { _: Throwable ->
                characterDao.getCharacterById(mId).map {
                    entityToDomainMapper(it)
                }
            }
        }
    }
}