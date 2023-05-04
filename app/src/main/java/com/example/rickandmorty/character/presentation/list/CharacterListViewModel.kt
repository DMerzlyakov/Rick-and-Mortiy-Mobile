package com.example.rickandmorty.character.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmorty.character.domain.list.GetCharacterListByIdUseCase
import com.example.rickandmorty.character.domain.list.GetCharacterListUseCase
import com.example.rickandmorty.character.domain.list.model.CharacterFilter
import com.example.rickandmorty.character.presentation.list.mapper.toCharacterItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getCharacterListByIdUseCase: GetCharacterListByIdUseCase
) : ViewModel() {


    private val searchByFilter = MutableLiveData(CharacterFilter(""))

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    suspend fun getFullListCharacter() = searchByFilter.asFlow()
        .debounce(500)
        .flatMapLatest {
            getCharacterListUseCase(it.name, it.status, it.species, it.gender)
                .map { pagingData ->
                    pagingData.map { item -> item.toCharacterItem() }
                }
        }
        .cachedIn(viewModelScope)


    suspend fun getListCharacterById(idList: List<Int>) =
            getCharacterListByIdUseCase(idList)
                .map { pagingData ->
                    pagingData.map { item -> item.toCharacterItem() }
                }.flowOn(Dispatchers.IO)
                .cachedIn(viewModelScope)


    fun setSearchByFilter(item: CharacterFilter) {
        searchByFilter.value = item
    }

}