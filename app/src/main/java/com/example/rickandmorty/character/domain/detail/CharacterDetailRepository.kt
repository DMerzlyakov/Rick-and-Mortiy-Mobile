package com.example.rickandmorty.character.domain.detail

import com.example.rickandmorty.character.data.detail.remote.model.CharacterDetailDTO
import io.reactivex.Observable

interface CharacterDetailRepository {

    fun getCharacterDetail(mId: Int): Observable<CharacterDetail>
}