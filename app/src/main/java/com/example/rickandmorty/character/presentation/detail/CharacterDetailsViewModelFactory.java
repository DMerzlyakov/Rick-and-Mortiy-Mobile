package com.example.rickandmorty.character.presentation.detail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.character.domain.detail.CharacterDetailRepository;

import javax.inject.Inject;

public class CharacterDetailsViewModelFactory implements ViewModelProvider.Factory {


    private final CharacterDetailRepository characterDetailRepository;
    @Inject
    public CharacterDetailsViewModelFactory(CharacterDetailRepository characterDetailRepository) {
        this.characterDetailRepository = characterDetailRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CharacterDetailViewModel.class)) {
            return (T) new CharacterDetailViewModel(characterDetailRepository);
        } else {
            throw new RuntimeException("Unknown view model class");
        }
    }
}