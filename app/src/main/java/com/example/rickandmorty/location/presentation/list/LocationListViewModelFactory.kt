package com.example.rickandmorty.location.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.location.domain.list.GetLocationListUseCase
import javax.inject.Inject

class LocationListViewModelFactory @Inject constructor(
    private val getLocationListUseCase: GetLocationListUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LocationsListViewModel::class.java)) {
            LocationsListViewModel(getLocationListUseCase) as T
        } else {
            throw RuntimeException("Unknown view model class")
        }
    }
}