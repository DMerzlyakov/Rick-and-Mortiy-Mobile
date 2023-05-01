package com.example.rickandmorty.character.domain.detail

import com.example.rickandmorty.character.domain.detail.model.CharacterDetail
import io.reactivex.Observable

interface GetCharacterDetailUseCase {
    operator fun invoke(mId: Int): Observable<CharacterDetail>
}