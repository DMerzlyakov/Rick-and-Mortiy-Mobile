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
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getCharacterListByIdUseCase: GetCharacterListByIdUseCase
) : ViewModel() {


    private val searchByFilter = MutableLiveData(CharacterFilter(""))

    fun getFullListCharacter() = searchByFilter.asFlow()
        // if user types text too quickly -> filtering intermediate values to avoid excess loads
        .debounce(500)
        .flatMapLatest {
            getCharacterListUseCase(it.name, it.status, it.species, it.gender)
                .map { pagingData ->
                    pagingData.map { item -> item.toCharacterItem() }
                }
        }
        // always use cacheIn operator for flows returned by Pager. Otherwise exception may be thrown
        // when 1) refreshing/invalidating or 2) subscribing to the flow more than once.
        .cachedIn(viewModelScope)

    fun getListCharacterById(idList: List<Int>) = searchByFilter.asFlow()
        // if user types text too quickly -> filtering intermediate values to avoid excess loads
        .debounce(500)
        .flatMapLatest {
            getCharacterListByIdUseCase(idList)
                .map { pagingData ->
                    pagingData.map { item -> item.toCharacterItem() }
                }
        }
        // always use cacheIn operator for flows returned by Pager. Otherwise exception may be thrown
        // when 1) refreshing/invalidating or 2) subscribing to the flow more than once.
        .cachedIn(viewModelScope)


    fun setSearchByFilter(item: CharacterFilter) {
        searchByFilter.value = item
    }

}