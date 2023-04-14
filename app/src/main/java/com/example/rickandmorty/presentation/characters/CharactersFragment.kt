package com.example.rickandmorty.presentation.characters

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.presentation.CustomItemDecorator
import com.example.rickandmorty.presentation.OnClickRecyclerViewInterface

class CharactersFragment : Fragment() {


    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding
        get() = _binding ?: throw RuntimeException("FragmentCharactersBinding is null")

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this)[CharactersViewModel::class.java]
    }

    private val onClickRecyclerViewInterface = object : OnClickRecyclerViewInterface<Character> {
        override fun onItemClick(item: Character, position: Int) {
            TODO("Not yet implemented")
        }
    }

    private val adapter = CharactersRecyclerViewAdapter(onClickRecyclerViewInterface)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialRecycleView()
    }


    /** Установка адаптера для RecyclerView*/
    private fun initialRecycleView() = with(binding) {
        characterList.layoutManager = LinearLayoutManager(requireContext())
        characterList.adapter = adapter
        val dividerItemDecoration = CustomItemDecorator(1, 10)
        characterList.addItemDecoration(dividerItemDecoration)
//        adapter.submitList(ITEMS.toList())
    }

    companion object {
        fun newInstance() = CharactersFragment()
    }
}