package com.example.rickandmorty.character.data.detail

import com.example.rickandmorty.character.data.detail.mapper.CharacterDetailDtoToCharacterDetailMapper
import com.example.rickandmorty.character.data.detail.remote.CharacterDetailApi
import com.example.rickandmorty.character.domain.detail.CharacterDetail
import com.example.rickandmorty.character.domain.detail.CharacterDetailRepository
import io.reactivex.Observable

class CharacterDetailRepositoryImpl(private val characterDetailApi: CharacterDetailApi): CharacterDetailRepository {

    private val toCharacterDetailMapper = CharacterDetailDtoToCharacterDetailMapper()


    override fun getCharacterDetail(mId: Int): Observable<CharacterDetail> {
        return characterDetailApi.getDetailCharacter(mId).map {
            toCharacterDetailMapper(it)
        }
    }
}