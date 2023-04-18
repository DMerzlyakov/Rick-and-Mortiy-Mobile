package com.example.rickandmorty.presentation.character_details;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.databinding.FragmentCharacterDetailsBinding;
import com.example.rickandmorty.presentation.OnNavigationListener;

public class CharacterDetailsFragment extends Fragment {

    private OnNavigationListener onNavigationListener;
    private FragmentCharacterDetailsBinding binding;
    private CharacterDetailViewModel viewModel;
    private int mId;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNavigationListener) {
            onNavigationListener = (OnNavigationListener) context;
        } else {
            throw new RuntimeException("Activity must be implements OnNavigationListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mId = requireArguments().getInt(CharacterDetailsFragment.ARG_PARAM_CHARACTER_ID);

        viewModel = new ViewModelProvider(this, new CharacterDetailsViewModelFactory(mId))
                .get(CharacterDetailViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false);

        setupListeners();

        return binding.getRoot();
    }

    private void setupListeners() {
        binding.btnBack.setOnClickListener(view -> onNavigationListener.toBackStack());
    }


    public static final String ARG_PARAM_CHARACTER_ID = "id";

    public static CharacterDetailsFragment newInstance(int mId) {
        CharacterDetailsFragment fragment = new CharacterDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_CHARACTER_ID, mId);
        fragment.setArguments(args);
        return fragment;
    }
}
