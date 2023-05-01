package com.example.rickandmorty.location.presentation.list

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmorty.location.di.LocationListRepositoryObject
import com.example.rickandmorty.location.domain.list.LocationsListRepository
import com.example.rickandmorty.location.domain.list.model.LocationFilter
import com.example.rickandmorty.location.presentation.list.mapper.LocationDomainToLocationUiModelMapper
import com.example.rickandmorty.location.presentation.list.model.LocationUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class LocationsListViewModel : ViewModel() {


//    val usersFlow: Flow<PagingData<LocationUiModel>>
    lateinit var usersFlow: Flow<PagingData<LocationUiModel>>

    private val searchByFilter = MutableLiveData(LocationFilter(""))

    private lateinit var locationsRepository: LocationsListRepository

    private val locationDomainToLocationUiModelMapper = LocationDomainToLocationUiModelMapper()

//    init {
    fun getData(){
        usersFlow = searchByFilter.asFlow()
            // if user types text too quickly -> filtering intermediate values to avoid excess loads
            .debounce(500)
            .flatMapLatest {
                locationsRepository.getPagedLocations(it.name, it.type, it.dimension)
                    .map { pagingData ->
                        pagingData.map { item -> locationDomainToLocationUiModelMapper(item) }
                    }
            }
            // always use cacheIn operator for flows returned by Pager. Otherwise exception may be thrown
            // when 1) refreshing/invalidating or 2) subscribing to the flow more than once.
            .cachedIn(viewModelScope)
    }

    fun initRepository(context: Context){
        locationsRepository = LocationListRepositoryObject.getLocationRepository(context)
    }
    fun setSearchByFilter(item: LocationFilter) {
        searchByFilter.value = item
    }

}