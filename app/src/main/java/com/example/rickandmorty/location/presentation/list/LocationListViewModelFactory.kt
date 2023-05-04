package com.example.rickandmorty.location.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.location.domain.list.GetLocationListUseCase
import com.example.rickandmorty.location.presentation.list.mapper.LocationDomainToLocationUiMapper
import javax.inject.Inject

class LocationListViewModelFactory @Inject constructor(
    private val getLocationListUseCase: GetLocationListUseCase,
    private val locationDomainToLocationUiMapper: LocationDomainToLocationUiMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LocationListViewModel::class.java)) {
            LocationListViewModel(getLocationListUseCase, locationDomainToLocationUiMapper) as T
        } else {
            throw RuntimeException("Unknown view model class")
        }
    }
}