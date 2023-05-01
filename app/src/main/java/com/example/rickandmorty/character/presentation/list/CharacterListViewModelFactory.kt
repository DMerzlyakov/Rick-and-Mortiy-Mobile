package com.example.rickandmorty.character.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.character.domain.list.CharacterListRepository
import com.example.rickandmorty.character.domain.list.GetCharacterListUseCase
import javax.inject.Inject


class CharacterListViewModelFactory @Inject constructor(private val getCharacterListUseCase: GetCharacterListUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CharactersListViewModel::class.java)) {
            CharactersListViewModel(getCharacterListUseCase) as T
        } else {
            throw RuntimeException("Unknown view model class")
        }
    }
}