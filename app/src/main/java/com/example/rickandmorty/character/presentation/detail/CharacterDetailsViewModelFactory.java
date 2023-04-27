package com.example.rickandmorty.character.presentation.detail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CharacterDetailsViewModelFactory implements ViewModelProvider.Factory {

    private final int mId;

    public CharacterDetailsViewModelFactory(int mId) {
        this.mId = mId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CharacterDetailViewModel.class)) {
            return (T) new CharacterDetailViewModel(mId);
        } else {
            throw new RuntimeException("Unknown view model class");
        }
    }
}