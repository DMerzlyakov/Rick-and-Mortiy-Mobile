package com.example.rickandmorty.episode.presentation.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.episode.di.list.DaggerEpisodeListComponent
import com.example.rickandmorty.episode.domain.list.model.EpisodeFilter
import com.example.rickandmorty.episode.presentation.list.model.EpisodeUi
import com.example.rickandmorty.episode.presentation.list.recyclerView.EpisodesRecyclerViewAdapter
import com.example.rickandmorty.extention_util.OnClickRecyclerViewInterface
import com.example.rickandmorty.main.presentation.OnNavigationListener
import com.example.rickandmorty.main.presentation.RickAndMortyApp
import com.example.rickandmorty.universal_filter.FilterFragment
import com.example.rickandmorty.universal_filter.OnFilterResultListenerEpisode
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

class EpisodeListFragment : Fragment(){

    private lateinit var onNavigationListener: OnNavigationListener
    private var _binding: FragmentEpisodesBinding? = null
    private val binding: FragmentEpisodesBinding
        get() = _binding ?: throw RuntimeException("FragmentEpisodesBinding is null")

    private val component by lazy {
        DaggerEpisodeListComponent.factory().create((requireActivity().application as RickAndMortyApp).component)
    }

    @Inject
    lateinit var viewModelFactory: EpisodeListViewModelFactory

    private val viewModel: EpisodeListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[EpisodeListViewModel::class.java]
    }

    private val onClickRecyclerViewInterface = object :
        OnClickRecyclerViewInterface<EpisodeUi> {
        override fun onItemClick(item: EpisodeUi, position: Int) {
//            val fragment = LocationDetailFragment.newInstance(item.id)
//            onNavigationListener.navigateToFragment(fragment)
        }
    }

    private val adapter = EpisodesRecyclerViewAdapter(onClickRecyclerViewInterface)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNavigationListener) {
            onNavigationListener = context
        } else {
            throw RuntimeException("Activity must be implements OnNavigationListener")
        }

        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialRecycleView()

        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            searchView.editText?.addTextChangedListener{
                viewModel.setSearchByFilter(EpisodeFilter(it.toString()))
            }

            filterBtn.setOnClickListener {
                val dialog = FilterFragment.newInstance(
                    FilterFragment.Companion.TYPE.FROM_EPISODE_LIST,
                )

                dialog.setOnFilterResultListenerEpisode(object : OnFilterResultListenerEpisode {
                    override fun confirmFilter(item: EpisodeFilter?) {
                        item?.let {
                            binding.refreshLayout.isRefreshing = true
                            viewModel.setSearchByFilter(it) }
                    }
                })
                dialog.show(childFragmentManager, "Filter")
            }

            refreshLayout.setOnRefreshListener {
                binding.circularProgressBar.visibility = View.INVISIBLE
                adapter.refresh()
            }


        }
    }

    private fun initialStateListener() {
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Error) {
                val error = loadState.refresh as LoadState.Error
                when (error.error) {
                    is UnknownHostException -> makeToast("Connection Error. Load from cache")
                    is NullPointerException -> makeToast("Elements not found")
                    else -> return@addLoadStateListener
                }
            }
            with(binding){
                if (circularProgressBar.isVisible){
                    circularProgressBar.isVisible =  loadState.refresh is LoadState.Loading
                }
                if (refreshLayout.isRefreshing){
                    refreshLayout.isRefreshing =  loadState.refresh is LoadState.Loading
                }
            }
        }
    }

    private fun makeToast(message: String) {
        Snackbar.make(binding.constraintLayout, message, Snackbar.LENGTH_SHORT).show()
    }


    /** Установка адаптера для RecyclerView*/
    private fun initialRecycleView() = with(binding) {
        episodeList.layoutManager = GridLayoutManager(requireContext(), 2)
        episodeList.adapter = adapter

        observeLocations()
        initialStateListener()
    }

    private fun observeLocations() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.episodesFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    companion object {
        fun newInstance() = EpisodeListFragment()
    }
}