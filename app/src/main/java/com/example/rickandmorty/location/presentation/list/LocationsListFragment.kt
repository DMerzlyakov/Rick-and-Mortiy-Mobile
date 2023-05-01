package com.example.rickandmorty.location.presentation.list

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
import com.example.rickandmorty.databinding.FragmentLocationsBinding
import com.example.rickandmorty.extention_util.OnClickRecyclerViewInterface
import com.example.rickandmorty.location.di.list.DaggerLocationListComponent
import com.example.rickandmorty.location.domain.list.model.LocationFilter
import com.example.rickandmorty.location.presentation.list.model.LocationUiModel
import com.example.rickandmorty.location.presentation.list.recycler_view.LocationsRecyclerViewAdapter
import com.example.rickandmorty.main.presentation.OnNavigationListener
import com.example.rickandmorty.main.presentation.RickAndMortyApp
import com.example.rickandmorty.universal_filter.FilterFragment
import com.example.rickandmorty.universal_filter.FilterFragment.Companion.TYPE.FROM_LOCATION_LIST
import com.example.rickandmorty.universal_filter.OnFilterResultListenerLocation
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

class LocationsListFragment : Fragment(){

    private lateinit var onNavigationListener: OnNavigationListener
    private var _binding: FragmentLocationsBinding? = null
    private val binding: FragmentLocationsBinding
        get() = _binding ?: throw RuntimeException("FragmentLocationsBinding is null")




    private val component by lazy {
        DaggerLocationListComponent.factory().create((requireActivity().application as RickAndMortyApp).component)
    }

    @Inject
    lateinit var viewModelFactory: LocationListViewModelFactory

    private val viewModel: LocationsListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[LocationsListViewModel::class.java]
    }


    private val onClickRecyclerViewInterface = object :
        OnClickRecyclerViewInterface<LocationUiModel> {
        override fun onItemClick(item: LocationUiModel, position: Int) {
//            val fragment = CharacterDetailsFragment.newInstance(item.id)
//            onNavigationListener.navigateToFragment(fragment)
        }
    }

    private val adapter = LocationsRecyclerViewAdapter(onClickRecyclerViewInterface)

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
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)

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
                viewModel.setSearchByFilter(LocationFilter(it.toString()))
            }

            filterBtn.setOnClickListener {
                val dialog = FilterFragment.newInstance(
                    FROM_LOCATION_LIST,
                )

                dialog.setOnFilterResultListenerLocation(object : OnFilterResultListenerLocation {
                    override fun confirmFilter(item: LocationFilter?) {
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
        locationList.layoutManager = GridLayoutManager(requireContext(), 2)
        locationList.adapter = adapter

        observeLocations()
        initialStateListener()
    }

    private fun observeLocations() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.usersFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    companion object {
        fun newInstance() = LocationsListFragment()
    }

}