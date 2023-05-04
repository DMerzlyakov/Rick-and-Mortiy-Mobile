package com.example.rickandmorty.character.domain.detail

import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain
import io.reactivex.Single
import javax.inject.Inject

class GetCharacterDetailUseCaseImpl @Inject constructor(
    private val characterDetailRepository: CharacterDetailRepository
) : GetCharacterDetailUseCase {
    override fun invoke(mId: Int): Single<CharacterDetailDomain> {
        return characterDetailRepository.getCharacterDetail(mId)
    }

}