package com.example.rickandmorty.character.domain.detail

import androidx.paging.PagingData
import com.example.rickandmorty.character.domain.detail.model.CharacterDetail
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterDetailUseCaseImpl @Inject constructor(
    private val characterDetailRepository: CharacterDetailRepository
) : GetCharacterDetailUseCase {
    override suspend fun invoke(mId: Int): Observable<CharacterDetail> {
        return characterDetailRepository.getCharacterDetail(mId)
    }

}