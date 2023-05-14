package com.example.rickandmorty.location.presentation.detail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.location.domain.detail.GetLocationDetailUseCase;
import com.example.rickandmorty.location.presentation.detail.mapper.LocationDetailDomainToLocationDetailUiMapper;

import javax.inject.Inject;

public class LocationDetailViewModelFactory implements ViewModelProvider.Factory {

    private final GetLocationDetailUseCase getLocationDetailUseCase;
    private final LocationDetailDomainToLocationDetailUiMapper mapper;

    @Inject
    public LocationDetailViewModelFactory(GetLocationDetailUseCase getLocationDetailUseCase, LocationDetailDomainToLocationDetailUiMapper mapper) {
        this.getLocationDetailUseCase = getLocationDetailUseCase;
        this.mapper = mapper;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LocationDetailViewModel.class)) {
            return (T) new LocationDetailViewModel(getLocationDetailUseCase, mapper);
        } else {
            throw new RuntimeException("Unknown view model class");
        }
    }
}