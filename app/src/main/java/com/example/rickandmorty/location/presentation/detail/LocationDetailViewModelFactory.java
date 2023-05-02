package com.example.rickandmorty.location.presentation.detail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.character.domain.detail.GetCharacterDetailUseCase;
import com.example.rickandmorty.character.presentation.detail.CharacterDetailViewModel;
import com.example.rickandmorty.location.domain.detail.GetLocationDetailUseCase;

import javax.inject.Inject;

public class LocationDetailViewModelFactory implements ViewModelProvider.Factory {


    private final GetLocationDetailUseCase getLocationDetailUseCase;

    @Inject
    public LocationDetailViewModelFactory(GetLocationDetailUseCase getLocationDetailUseCase) {
        this.getLocationDetailUseCase = getLocationDetailUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LocationDetailViewModel.class)) {
            return (T) new LocationDetailViewModel(getLocationDetailUseCase);
        } else {
            throw new RuntimeException("Unknown view model class");
        }
    }
}