package com.example.rickandmorty.character.domain.detail

import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain
import io.reactivex.Observable

interface CharacterDetailRepository {

    fun getCharacterDetail(mId: Int): Observable<CharacterDetailDomain>
}