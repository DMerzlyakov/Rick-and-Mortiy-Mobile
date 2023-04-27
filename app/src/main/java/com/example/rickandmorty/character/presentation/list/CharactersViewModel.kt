package com.example.rickandmorty.character.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmorty.character.di.CharacterListRepositoryObject
import com.example.rickandmorty.character.domain.list.model.CharactersFilter
import com.example.rickandmorty.character.presentation.list.mapper.CharacterDomainToCharacterItemMapper
import com.example.rickandmorty.character.presentation.list.model.CharacterItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class CharactersViewModel : ViewModel() {

    val usersFlow: Flow<PagingData<CharacterItem>>

    private val searchByFilter = MutableLiveData(CharactersFilter(""))

    private val usersRepository = CharacterListRepositoryObject.getCharacterRepository()

    private val characterDomainToCharacterItemMapper = CharacterDomainToCharacterItemMapper()

    init {
        usersFlow = searchByFilter.asFlow()
            // if user types text too quickly -> filtering intermediate values to avoid excess loads
            .debounce(500)
            .flatMapLatest {
                usersRepository.getPagedCharacters(it.name, it.status, it.species, it.gender)
                    .map { pagingData ->
                        pagingData.map { item -> characterDomainToCharacterItemMapper(item) }
                    }
            }
            // always use cacheIn operator for flows returned by Pager. Otherwise exception may be thrown
            // when 1) refreshing/invalidating or 2) subscribing to the flow more than once.
            .cachedIn(viewModelScope)
    }

    fun setSearchByFilter(item: CharactersFilter) {
        searchByFilter.value = item
    }

}