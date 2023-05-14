package com.example.rickandmorty.character.presentation.detail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.character.domain.detail.GetCharacterDetailUseCase;
import com.example.rickandmorty.character.presentation.detail.mapper.CharacterDetailDomainToCharacterDetailUiMapper;

import javax.inject.Inject;

public class CharacterDetailsViewModelFactory implements ViewModelProvider.Factory {


    private final GetCharacterDetailUseCase getCharacterDetailUseCase;
    private final CharacterDetailDomainToCharacterDetailUiMapper mapper;

    @Inject
    public CharacterDetailsViewModelFactory(GetCharacterDetailUseCase getCharacterDetailUseCase, CharacterDetailDomainToCharacterDetailUiMapper mapper) {
        this.getCharacterDetailUseCase = getCharacterDetailUseCase;
        this.mapper = mapper;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CharacterDetailViewModel.class)) {
            return (T) new CharacterDetailViewModel(getCharacterDetailUseCase, mapper);
        } else {
            throw new RuntimeException("Unknown view model class");
        }
    }
}