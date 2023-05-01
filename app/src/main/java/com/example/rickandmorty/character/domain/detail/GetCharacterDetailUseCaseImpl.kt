package com.example.rickandmorty.character.domain.detail

import com.example.rickandmorty.character.domain.detail.model.CharacterDetail
import io.reactivex.Observable
import javax.inject.Inject

class GetCharacterDetailUseCaseImpl @Inject constructor(
    private val characterDetailRepository: CharacterDetailRepository
) : GetCharacterDetailUseCase {
    override fun invoke(mId: Int): Observable<CharacterDetail> {
        return characterDetailRepository.getCharacterDetail(mId)
    }

}