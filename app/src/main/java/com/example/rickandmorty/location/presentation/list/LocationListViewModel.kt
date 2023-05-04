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
import com.example.rickandmorty.location.presentation.list.mapper.LocationDomainToLocationUiMapper
import com.example.rickandmorty.location.presentation.list.model.LocationUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class LocationListViewModel @Inject constructor(
    private val getLocationListUseCase: GetLocationListUseCase,
    private val locationDomainToLocationUiMapper: LocationDomainToLocationUiMapper
) : ViewModel() {

    val locationFlow: Flow<PagingData<LocationUi>>

    private val searchByFilter = MutableLiveData(LocationFilter(""))

    init {
        locationFlow = searchByFilter.asFlow()
            .debounce(500)
            .flatMapLatest {
                getLocationListUseCase(it.name, it.type, it.dimension)
                    .map { pagingData ->
                        pagingData.map { item -> locationDomainToLocationUiMapper(item) }
                    }
            }
            .cachedIn(viewModelScope)
    }

    fun setSearchByFilter(item: LocationFilter) {
        searchByFilter.value = item
    }

}