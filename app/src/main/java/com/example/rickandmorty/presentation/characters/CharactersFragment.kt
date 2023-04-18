package com.example.rickandmorty.presentation.characters

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.CharactersFilter
import com.example.rickandmorty.presentation.FilterFragment
import com.example.rickandmorty.presentation.OnFilterResultListener
import com.example.rickandmorty.presentation.OnClickRecyclerViewInterface
import com.example.rickandmorty.presentation.OnNavigationListener
import com.example.rickandmorty.presentation.character_details.CharacterDetailsFragment
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

    private val onClickRecyclerViewInterface = object : OnClickRecyclerViewInterface<Character> {
        override fun onItemClick(item: Character, position: Int) {
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
                viewModel.setSearchByFilter(CharactersFilter(""))
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

