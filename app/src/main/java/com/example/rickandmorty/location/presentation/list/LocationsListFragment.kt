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
import com.example.rickandmorty.location.domain.list.model.LocationFilter
import com.example.rickandmorty.location.presentation.list.model.LocationUiModel
import com.example.rickandmorty.location.presentation.list.recycler_view.LocationsRecyclerViewAdapter
import com.example.rickandmorty.main.OnNavigationListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class LocationsListFragment : Fragment(){

    private lateinit var onNavigationListener: OnNavigationListener
    private var _binding: FragmentLocationsBinding? = null
    private val binding: FragmentLocationsBinding
        get() = _binding ?: throw RuntimeException("FragmentLocationsBinding is null")


    private val viewModel: LocationsListViewModel by lazy {
        ViewModelProvider(this)[LocationsListViewModel::class.java]
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

//            filterBtn.setOnClickListener {
//                val dialog = FilterFragment.newInstance(
//                    FilterFragment.FROM_CHARACTER,
//                    searchView.editText?.text.toString()
//                )
//
//                dialog.setOnFilterResultListener(object : OnFilterResultListener {
//                    override fun confirmFilter(item: CharactersFilter?) {
//                        item?.let {
//                            binding.refreshLayout.isRefreshing = true
//                            viewModel.setSearchByFilter(it) }
//                    }
//                })
//                dialog.show(childFragmentManager, "Filter")
//            }
//
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
        viewModel.initRepository(requireContext())
        viewModel.getData()
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