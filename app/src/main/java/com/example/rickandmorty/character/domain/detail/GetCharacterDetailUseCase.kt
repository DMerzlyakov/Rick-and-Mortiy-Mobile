package com.example.rickandmorty.character.domain.detail

import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain
import io.reactivex.Observable
import io.reactivex.Single

interface GetCharacterDetailUseCase {
    operator fun invoke(mId: Int): Single<CharacterDetailDomain>
}