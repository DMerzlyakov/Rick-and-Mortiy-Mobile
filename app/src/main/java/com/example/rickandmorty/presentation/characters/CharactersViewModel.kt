package com.example.rickandmorty.presentation.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.remote.CharacterApi
import com.example.rickandmorty.data.remote.RetrofitClient
import com.example.rickandmorty.data.repository.RepositoryImpl
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.CharactersFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {

    val usersFlow: Flow<PagingData<Character>>

    private val searchByFilter = MutableLiveData(CharactersFilter(""))

    private val usersRepository = RepositoryImpl(
            Dispatchers.IO,
            RetrofitClient.getClient().create(CharacterApi::class.java)
        )
    init {
        usersFlow = searchByFilter.asFlow()
            // if user types text too quickly -> filtering intermediate values to avoid excess loads
            .debounce(500)
            .flatMapLatest {
                usersRepository.getPagedCharacters(it.name, it.status, it.species, it.gender)
            }
            // always use cacheIn operator for flows returned by Pager. Otherwise exception may be thrown
            // when 1) refreshing/invalidating or 2) subscribing to the flow more than once.
            .cachedIn(viewModelScope)
    }

    fun setSearchByFilter(item: CharactersFilter) {
        searchByFilter.value = item
    }

}