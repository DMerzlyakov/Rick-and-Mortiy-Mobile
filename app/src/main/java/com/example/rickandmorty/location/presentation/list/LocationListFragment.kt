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
import com.example.rickandmorty.databinding.FragmentLocationListBinding
import com.example.rickandmorty.location.di.DaggerLocationComponent
import com.example.rickandmorty.location.domain.list.model.LocationFilter
import com.example.rickandmorty.location.presentation.detail.LocationDetailFragment
import com.example.rickandmorty.location.presentation.list.model.LocationUi
import com.example.rickandmorty.location.presentation.list.recycler_view.LocationsRecyclerViewAdapter
import com.example.rickandmorty.main.presentation.OnNavigationListener
import com.example.rickandmorty.main.presentation.RickAndMortyApp
import com.example.rickandmorty.universal_filter.FilterFragment
import com.example.rickandmorty.universal_filter.FilterFragment.Companion.TYPE.FROM_LOCATION_LIST
import com.example.rickandmorty.universal_filter.OnFilterResultListenerLocation
import com.example.rickandmorty.utils.OnClickRecyclerViewInterface
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

class LocationListFragment : Fragment() {

    private lateinit var onNavigationListener: OnNavigationListener

    private var _binding: FragmentLocationListBinding? = null
    private val binding: FragmentLocationListBinding
        get() = _binding ?: throw RuntimeException("FragmentLocationsBinding is null")

    private val component by lazy {
        DaggerLocationComponent.factory()
            .create((requireActivity().application as RickAndMortyApp).component)
    }

    @Inject
    lateinit var viewModelFactory: LocationListViewModelFactory

    private val viewModel: LocationListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[LocationListViewModel::class.java]
    }


    private val onClickRecyclerViewInterface = object : OnClickRecyclerViewInterface<LocationUi> {
        override fun onItemClick(item: LocationUi, position: Int) {
            val fragment = LocationDetailFragment.newInstance(item.id)
            onNavigationListener.navigateToFragment(fragment)
            onNavigationListener.updateBottomNavigationVisibility(View.GONE)
        }
    }

    private val adapter = LocationsRecyclerViewAdapter(onClickRecyclerViewInterface)

    override fun onAttach(context: Context) {

        if (context is OnNavigationListener) {
            onNavigationListener = context
        } else {
            throw RuntimeException("Activity must be implements OnNavigationListener")
        }

        component.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLocationListBinding.inflate(inflater, container, false)
        onNavigationListener.updateBottomNavigationVisibility(View.VISIBLE)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initialRecycleView()
        initListeners()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initListeners() {
        with(binding) {
            searchView.editText?.addTextChangedListener {
                viewModel.setSearchByFilter(LocationFilter(it.toString()))
            }

            filterBtn.setOnClickListener(this@LocationListFragment::startFilterFragment)

            refreshLayout.setOnRefreshListener {
                binding.circularProgressBar.visibility = View.INVISIBLE
                adapter.refresh()
            }

        }
    }

    private fun startFilterFragment(view: View) {

        val dialog = FilterFragment.newInstance(FROM_LOCATION_LIST)

        dialog.setOnFilterResultListenerLocation(object : OnFilterResultListenerLocation {
            override fun confirmFilter(item: LocationFilter?) {
                item?.let {
                    binding.searchView.editText?.setText(it.name)
                    binding.refreshLayout.isRefreshing = true
                    viewModel.setSearchByFilter(it)

                }
            }
        })

        dialog.show(childFragmentManager, "Filter")

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

    private fun makeToast(message: String) =
        Snackbar.make(binding.constraintLayout, message, Snackbar.LENGTH_SHORT).show()


    /** Установка адаптера для RecyclerView*/
    private fun initialRecycleView() = with(binding) {
        locationList.layoutManager = GridLayoutManager(requireContext(), 2)
        locationList.adapter = adapter

        observeLocations()
        initialStateListener()
    }

    private fun observeLocations() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.locationFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    companion object {
        fun newInstance() = LocationListFragment()
    }

}