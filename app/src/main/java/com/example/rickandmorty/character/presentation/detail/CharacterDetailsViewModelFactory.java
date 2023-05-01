package com.example.rickandmorty.character.presentation.detail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.character.domain.detail.GetCharacterDetailUseCase;

import javax.inject.Inject;

public class CharacterDetailsViewModelFactory implements ViewModelProvider.Factory {


    private final GetCharacterDetailUseCase getCharacterDetailUseCase;

    @Inject
    public CharacterDetailsViewModelFactory(GetCharacterDetailUseCase getCharacterDetailUseCase) {
        this.getCharacterDetailUseCase = getCharacterDetailUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CharacterDetailViewModel.class)) {
            return (T) new CharacterDetailViewModel(getCharacterDetailUseCase);
        } else {
            throw new RuntimeException("Unknown view model class");
        }
    }
}