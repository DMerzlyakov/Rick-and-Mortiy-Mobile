package com.example.rickandmorty.character.data.detail

import com.example.rickandmorty.character.data.detail.mapper.CharacterDetailDtoToCharacterDetailDomainMapper
import com.example.rickandmorty.character.data.detail.remote.CharacterDetailApi
import com.example.rickandmorty.character.domain.detail.CharacterDetailRepository
import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain
import io.reactivex.Observable

class CharacterDetailRepositoryImpl(private val characterDetailApi: CharacterDetailApi): CharacterDetailRepository {

    private val toCharacterDetailMapper = CharacterDetailDtoToCharacterDetailDomainMapper()


    override fun getCharacterDetail(mId: Int): Observable<CharacterDetailDomain> {
        return characterDetailApi.getDetailCharacter(mId).map {
            toCharacterDetailMapper(it)
        }
    }
}