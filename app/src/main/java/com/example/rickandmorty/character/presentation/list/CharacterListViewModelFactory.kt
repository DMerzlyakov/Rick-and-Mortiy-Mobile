package com.example.rickandmorty.character.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.character.domain.list.GetCharacterListByIdUseCase
import com.example.rickandmorty.character.domain.list.GetCharacterListUseCase
import javax.inject.Inject


class CharacterListViewModelFactory @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getCharacterListByIdUseCase: GetCharacterListByIdUseCase
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CharacterListViewModel::class.java)) {
            CharacterListViewModel(getCharacterListUseCase, getCharacterListByIdUseCase) as T
        } else {
            throw RuntimeException("Unknown view model class")
        }
    }
}