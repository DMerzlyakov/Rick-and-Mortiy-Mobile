package com.example.rickandmorty.character.presentation.detail;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.R;
import com.example.rickandmorty.character.di.CharacterComponent;
import com.example.rickandmorty.character.di.DaggerCharacterComponent;
import com.example.rickandmorty.character.presentation.detail.model.CharacterDetailUi;
import com.example.rickandmorty.databinding.FragmentCharacterDetailsBinding;
import com.example.rickandmorty.episode.presentation.list.EpisodeListFragment;
import com.example.rickandmorty.main.presentation.OnNavigationListener;
import com.example.rickandmorty.main.presentation.RickAndMortyApp;
import com.example.rickandmorty.utils.ExtensionsKt;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;


public class CharacterDetailsFragment extends Fragment {

    private OnNavigationListener onNavigationListener;
    private FragmentCharacterDetailsBinding binding;
    private CharacterDetailViewModel viewModel;
    private CharacterDetailUi mCharacter;


    @Inject
    CharacterDetailsViewModelFactory viewModelFactory;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        CharacterComponent component = DaggerCharacterComponent.factory().create(((RickAndMortyApp) requireActivity().getApplication()).getComponent());
        component.inject(this);
        if (context instanceof OnNavigationListener) {
            onNavigationListener = (OnNavigationListener) context;
        } else {
            throw new RuntimeException("Activity must be implements OnNavigationListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(CharacterDetailViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false);
        setupButtonListeners();
        observeData(requireArguments().getInt(CharacterDetailsFragment.ARG_PARAM_CHARACTER_ID));
        return binding.getRoot();
    }

    private void setupButtonListeners() {
        binding.btnBack.setOnClickListener(view -> onNavigationListener.toBackStack());
        binding.originLocationView.setOnClickListener(view -> {
            if (mCharacter.getOrigin().getId() != null) {
                onNavigationListener.navigateToLocationDetailFragment(mCharacter.getOrigin().getId());
            }
        });
        binding.lastLocationView.setOnClickListener(view -> {
            if (mCharacter.getLocation().getId() != null) {
                onNavigationListener.navigateToLocationDetailFragment(mCharacter.getLocation().getId());
            }
        });
        binding.refreshLayout.setOnRefreshListener(() -> {
                    if (mCharacter != null) {
                        viewModel.getCharacter(mCharacter.getId());
                    }
                }
        );
    }


    private void observeData(int mId) {
        viewModel.getCharacter(mId);
        viewModel.getCharacterLiveData().observe(getViewLifecycleOwner(), characterDetail -> {
            mCharacter = characterDetail;
            updateViewDetail();
            setupEpisodeList();
        });

        viewModel.getErrorLiveData().observe(getViewLifecycleOwner(), value -> {
            Snackbar.make(binding.getRoot(), value, Snackbar.LENGTH_SHORT).show();
        });
    }

    private void setupEpisodeList() {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.list_container, EpisodeListFragment.newInstance(EpisodeListFragment.getTypeListOnly(), mCharacter.getEpisodeIdList()))
                .commit();
    }

    private void updateViewDetail() {
        binding.refreshLayout.setRefreshing(false);
        binding.mainLayout.setVisibility(View.VISIBLE);
        binding.circularProgressBar.setVisibility(View.INVISIBLE);
        if (mCharacter != null) {
            binding.nameView.setText(mCharacter.getName());

            ExtensionsKt.setImageFromUrl(binding.avatarView, mCharacter.getUrlAvatar(), requireContext());

            binding.genreView.setText(mCharacter.getGender());
            binding.speciesView.setText(mCharacter.getSpecies());
            binding.statusView.setText(mCharacter.getStatus());
            binding.lastLocationView.setText(mCharacter.getLocation().getName());
            binding.originLocationView.setText(mCharacter.getOrigin().getName());
        }
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
