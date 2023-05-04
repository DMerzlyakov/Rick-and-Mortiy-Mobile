package com.example.rickandmorty.character.domain.detail

import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain
import io.reactivex.Single

interface CharacterDetailRepository {

    fun getCharacterDetail(mId: Int): Single<CharacterDetailDomain>
}