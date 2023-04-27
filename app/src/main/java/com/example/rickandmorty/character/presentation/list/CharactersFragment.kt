package com.example.rickandmorty.character.presentation.list

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import com.example.rickandmorty.character.domain.list.model.CharactersFilter
import com.example.rickandmorty.character.presentation.list.recycler_view.OnClickRecyclerViewInterface
import com.example.rickandmorty.character.presentation.detail.CharacterDetailsFragment
import com.example.rickandmorty.character.presentation.list.model.CharacterItem
import com.example.rickandmorty.character.presentation.list.recycler_view.CharactersRecyclerViewAdapter
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.main.OnNavigationListener
import com.example.rickandmorty.universal_filter.FilterFragment
import com.example.rickandmorty.universal_filter.OnFilterResultListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.UnknownHostException


class CharactersFragment : Fragment() {

    private lateinit var onNavigationListener: OnNavigationListener
    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding
        get() = _binding ?: throw RuntimeException("FragmentCharactersBinding is null")

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this)[CharactersViewModel::class.java]
    }

    private val onClickRecyclerViewInterface = object : OnClickRecyclerViewInterface<CharacterItem> {
        override fun onItemClick(item: CharacterItem, position: Int) {
            val fragment = CharacterDetailsFragment.newInstance(item.id)
            onNavigationListener.navigateToFragment(fragment)
        }
    }

    private val adapter = CharactersRecyclerViewAdapter(onClickRecyclerViewInterface)

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
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialRecycleView()

        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            searchView.editText?.addTextChangedListener {
                viewModel.setSearchByFilter(CharactersFilter(it.toString()))
            }

            filterBtn.setOnClickListener {
                val dialog = FilterFragment.newInstance(
                    FilterFragment.FROM_CHARACTER,
                    searchView.editText?.text.toString()
                )

                dialog.setOnFilterResultListener(object : OnFilterResultListener {
                    override fun confirmFilter(item: CharactersFilter?) {
                        item?.let { viewModel.setSearchByFilter(it) }
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

    private fun observeUsers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.usersFlow.collectLatest { pagingData ->
                Log.e("adapter", pagingData.toString())
                adapter.submitData(pagingData)

                binding.refreshLayout.isRefreshing = false
            }
        }
    }


    companion object {
        fun newInstance() = CharactersFragment()
    }

}

