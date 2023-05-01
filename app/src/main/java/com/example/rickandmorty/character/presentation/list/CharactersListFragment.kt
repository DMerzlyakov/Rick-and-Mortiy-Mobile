package com.example.rickandmorty.character.presentation.list

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
import com.example.rickandmorty.character.di.list.DaggerCharacterListComponent
import com.example.rickandmorty.character.domain.list.model.CharacterFilter
import com.example.rickandmorty.character.presentation.detail.CharacterDetailsFragment
import com.example.rickandmorty.character.presentation.list.model.CharacterUiModel
import com.example.rickandmorty.character.presentation.list.recycler_view.CharactersRecyclerViewAdapter
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.extention_util.OnClickRecyclerViewInterface
import com.example.rickandmorty.main.presentation.OnNavigationListener
import com.example.rickandmorty.main.presentation.RickAndMortyApp
import com.example.rickandmorty.universal_filter.FilterFragment
import com.example.rickandmorty.universal_filter.OnFilterResultListenerCharacter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject


class CharactersListFragment : Fragment() {

    private lateinit var onNavigationListener: OnNavigationListener

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding
        get() = _binding ?: throw RuntimeException("FragmentCharactersBinding is null")

    private val component by lazy {
        DaggerCharacterListComponent.factory().create((requireActivity().application as RickAndMortyApp).component)
    }


    @Inject
    lateinit var viewModelFactory: CharacterListViewModelFactory

    private val onClickRecyclerViewInterface = object :
        OnClickRecyclerViewInterface<CharacterUiModel> {
        override fun onItemClick(item: CharacterUiModel, position: Int) {
            val fragment = CharacterDetailsFragment.newInstance(item.id)
            onNavigationListener.navigateToFragment(fragment)
            onNavigationListener.updateBottomNavigationVisibility(View.GONE)
        }
    }

    private val viewModel: CharactersListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CharactersListViewModel::class.java]
    }

    private val adapter = CharactersRecyclerViewAdapter(onClickRecyclerViewInterface)

    private var fragmentType = TYPE.PARAM_WITH_SEARCH

    override fun onAttach(context: Context) {
        super.onAttach(context)

        component.inject(this)

        if (context is OnNavigationListener) {
            onNavigationListener = context
        } else {
            throw RuntimeException("Activity must be implements OnNavigationListener")
        }
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
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)

        setupFragmentType()

        return binding.root
    }

    private fun setupFragmentType() {
        when (fragmentType) {
            TYPE.PARAM_WITH_SEARCH -> {
                onNavigationListener.updateBottomNavigationVisibility(View.VISIBLE)
                binding.constraintLayout.visibility = View.VISIBLE
            }
            TYPE.PARAM_WITHOUT_SEARCH -> binding.constraintLayout.visibility = View.GONE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialRecycleView()
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            searchView.editText?.addTextChangedListener {
                viewModel.setSearchByFilter(CharacterFilter(it.toString()))
            }

            filterBtn.setOnClickListener {
                val dialog = FilterFragment.newInstance(
                    FilterFragment.Companion.TYPE.FROM_CHARACTER_LIST
                )

                dialog.setOnFilterResultListenerCharacter(object : OnFilterResultListenerCharacter {
                    override fun confirmFilter(item: CharacterFilter?) {
                        item?.let {
                            binding.refreshLayout.isRefreshing = true
                            viewModel.setSearchByFilter(it)
                        }
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

    /** Установка адаптера для RecyclerView*/
    private fun initialRecycleView() = with(binding) {
        characterList.layoutManager = GridLayoutManager(requireContext(), 2)
        characterList.adapter = adapter

        observeUsers()
        initialStateListener()
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
            with(binding) {
                if (circularProgressBar.isVisible) {
                    circularProgressBar.isVisible = loadState.refresh is LoadState.Loading
                }
                if (refreshLayout.isRefreshing) {
                    refreshLayout.isRefreshing = loadState.refresh is LoadState.Loading
                }
            }
        }
    }

    private fun makeToast(message: String) {
        Snackbar.make(binding.constraintLayout, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun observeUsers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.usersFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(SEARCH_PARAM: TYPE): CharactersListFragment {
            val fragment = CharactersListFragment()
            val args = Bundle()
            args.putSerializable(KEY_TYPE, SEARCH_PARAM)
            fragment.arguments = args
            return fragment
        }

        enum class TYPE {
            PARAM_WITH_SEARCH,
            PARAM_WITHOUT_SEARCH
        }

        @JvmStatic
        val WithSearchParam = TYPE.PARAM_WITH_SEARCH

        @JvmStatic
        val WithoutSearchParam = TYPE.PARAM_WITHOUT_SEARCH

        private const val KEY_TYPE = "type"
    }

}

