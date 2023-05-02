package com.example.rickandmorty.character.domain.detail

import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain
import io.reactivex.Observable
import javax.inject.Inject

class GetCharacterDetailUseCaseImpl @Inject constructor(
    private val characterDetailRepository: CharacterDetailRepository
) : GetCharacterDetailUseCase {
    override fun invoke(mId: Int): Observable<CharacterDetailDomain> {
        return characterDetailRepository.getCharacterDetail(mId)
    }

}