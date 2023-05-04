package com.example.rickandmorty.episode.presentation.list

import android.content.Context
import android.os.Build
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
import com.example.rickandmorty.databinding.FragmentEpisodeListBinding
import com.example.rickandmorty.episode.di.DaggerEpisodeComponent
import com.example.rickandmorty.episode.domain.list.model.EpisodeFilter
import com.example.rickandmorty.episode.presentation.detail.EpisodeDetailsFragment
import com.example.rickandmorty.episode.presentation.list.model.EpisodeUi
import com.example.rickandmorty.episode.presentation.list.recyclerView.EpisodesRecyclerViewAdapter
import com.example.rickandmorty.utils.OnClickRecyclerViewInterface
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
    private var _binding: FragmentEpisodeListBinding? = null
    private val binding: FragmentEpisodeListBinding
        get() = _binding ?: throw RuntimeException("FragmentEpisodesBinding is null")

    private val component by lazy {
        DaggerEpisodeComponent.factory().create((requireActivity().application as RickAndMortyApp).component)
    }

    @Inject
    lateinit var viewModelFactory: EpisodeListViewModelFactory

    private val viewModel: EpisodeListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[EpisodeListViewModel::class.java]
    }

    private val onClickRecyclerViewInterface = object :
        OnClickRecyclerViewInterface<EpisodeUi> {
        override fun onItemClick(item: EpisodeUi, position: Int) {
            val fragment = EpisodeDetailsFragment.newInstance(item.id)
            onNavigationListener.navigateToFragment(fragment)
            onNavigationListener.updateBottomNavigationVisibility(View.GONE)
        }
    }

    private val adapter = EpisodesRecyclerViewAdapter(onClickRecyclerViewInterface)

    private var fragmentType = TYPE.TYPE_FULL_SCREEN

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNavigationListener) {
            onNavigationListener = context
        } else {
            throw RuntimeException("Activity must be implements OnNavigationListener")
        }

        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            fragmentType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requireArguments().getSerializable(KEY_TYPE, TYPE::class.java) as TYPE
            } else {
                requireArguments().getSerializable(KEY_TYPE) as TYPE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEpisodeListBinding.inflate(inflater, container, false)
        setupFragmentType()

        return binding.root
    }

    private fun setupFragmentType() {
        when (fragmentType) {

            TYPE.TYPE_FULL_SCREEN -> {
                onNavigationListener.updateBottomNavigationVisibility(View.VISIBLE)
                binding.constraintLayout.visibility = View.VISIBLE

                observeFullEpisodeList()
            }
            TYPE.TYPE_ONLY_LIST_BY_ID -> {
                binding.constraintLayout.visibility = View.GONE

                val episodeIdList = requireArguments().getIntegerArrayList(KEY_ID_LIST)

                if (episodeIdList.isNullOrEmpty()){
                    binding.circularProgressBar.isVisible = false
                } else{
                    observeEpisodeListById(episodeIdList.toList())
                }

            }
        }
    }

    private fun observeFullEpisodeList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getFullListEpisode().collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun observeEpisodeListById(idList: List<Int>) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getListEpisodeById(idList).collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
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

        initialStateListener()
    }



    companion object {
        @JvmStatic
        fun newInstance(TYPE_PARAM: TYPE, EPISODE_LIST: List<Int> = emptyList()): EpisodeListFragment {
            val fragment = EpisodeListFragment()
            val args = Bundle()
            args.putSerializable(KEY_TYPE, TYPE_PARAM)

            if (EPISODE_LIST.isNotEmpty()){
                args.putIntegerArrayList(KEY_ID_LIST, ArrayList(EPISODE_LIST))
            }
            fragment.arguments = args
            return fragment
        }

        enum class TYPE {
            TYPE_ONLY_LIST_BY_ID,
            TYPE_FULL_SCREEN
        }

        @JvmStatic
        val TypeFullScreen = TYPE.TYPE_FULL_SCREEN

        @JvmStatic
        val TypeListOnly = TYPE.TYPE_ONLY_LIST_BY_ID

        private const val KEY_TYPE = "TYPE"

        private const val KEY_ID_LIST = "ID_LIST"
    }
}