package com.example.rickandmorty.episode.presentation.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.character.presentation.list.CharacterListFragment
import com.example.rickandmorty.databinding.FragmentEpisodeDetailsBinding
import com.example.rickandmorty.episode.di.DaggerEpisodeComponent
import com.example.rickandmorty.episode.presentation.detail.model.EpisodeDetailUi
import com.example.rickandmorty.main.presentation.OnNavigationListener
import com.example.rickandmorty.main.presentation.RickAndMortyApp
import javax.inject.Inject

class EpisodeDetailsFragment : Fragment() {

    private lateinit var onNavigationListener: OnNavigationListener
    private var _binding: FragmentEpisodeDetailsBinding? = null
    private val binding: FragmentEpisodeDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentEpisodeDetailsBinding is null")

    private val mId by lazy {
        requireArguments().getInt(ARG_PARAM_EPISODE_ID)
    }

    private val component by lazy {
        DaggerEpisodeComponent.factory()
            .create((requireActivity().application as RickAndMortyApp).component)
    }

    private var mEpisode: EpisodeDetailUi? = null

    @Inject
    lateinit var viewModelFactory: EpisodeDetailsViewModelFactory

    private val viewModel: EpisodeDetailViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[EpisodeDetailViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        component.inject(this)
        if (context is OnNavigationListener) {
            onNavigationListener = context
        } else {
            throw RuntimeException("Activity must be implements OnNavigationListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodeDetailsBinding.inflate(inflater, container, false)

        setupListeners()
        observeData(mId)

        return binding.root
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            onNavigationListener.toBackStack()
        }

        binding.refreshLayout.setOnRefreshListener { mEpisode?.id?.let { viewModel.getEpisode(it) } }
    }

    private fun observeData(episodeId: Int) {
        viewModel.getEpisode(episodeId)
        viewModel.episodeLiveData.observe(viewLifecycleOwner) { item ->
            mEpisode = item
            updateViewDetail()
        }
    }

    private fun setupCharacterList(characterIdList: List<Int>) =
        childFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                binding.characterListContainer.id,
                CharacterListFragment.newInstance(
                    CharacterListFragment.TypeListOnly,
                    characterIdList
                )
            )
            .commit()


    private fun updateViewDetail() {
        binding.refreshLayout.isRefreshing = false
        binding.circularProgressBar.visibility = View.INVISIBLE
        binding.mainLayout.visibility = View.VISIBLE

        mEpisode?.let {
            with(binding) {
                nameView.text = it.name
                airDateView.text = it.airDate
                episodeView.text = it.episode
            }

            setupCharacterList(it.characters)
        }
    }

    companion object {
        fun newInstance(mId: Int): EpisodeDetailsFragment {
            val fragment = EpisodeDetailsFragment()
            val args = Bundle()
            args.putInt(ARG_PARAM_EPISODE_ID, mId)
            fragment.arguments = args

            return fragment
        }

        const val ARG_PARAM_EPISODE_ID = "id"

    }
}