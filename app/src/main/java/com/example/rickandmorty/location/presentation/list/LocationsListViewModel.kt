package com.example.rickandmorty.location.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmorty.location.domain.list.GetLocationListUseCase
import com.example.rickandmorty.location.domain.list.model.LocationFilter
import com.example.rickandmorty.location.presentation.list.mapper.toLocationUiModel
import com.example.rickandmorty.location.presentation.list.model.LocationUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationsListViewModel @Inject constructor(
    private val getLocationListUseCase: GetLocationListUseCase
) : ViewModel() {


    var usersFlow: Flow<PagingData<LocationUiModel>>

    private val searchByFilter = MutableLiveData(LocationFilter(""))


    init {
        usersFlow = searchByFilter.asFlow()
            // if user types text too quickly -> filtering intermediate values to avoid excess loads
            .debounce(500)
            .flatMapLatest {
                getLocationListUseCase(it.name, it.type, it.dimension)
                    .map { pagingData ->
                        pagingData.map { item -> item.toLocationUiModel() }
                    }
            }
            // always use cacheIn operator for flows returned by Pager. Otherwise exception may be thrown
            // when 1) refreshing/invalidating or 2) subscribing to the flow more than once.
            .cachedIn(viewModelScope)
    }

    fun setSearchByFilter(item: LocationFilter) {
        searchByFilter.value = item
    }

}