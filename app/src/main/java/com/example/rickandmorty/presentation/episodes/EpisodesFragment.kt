package com.example.rickandmorty.presentation.episodes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.characters.CharactersFragment
import com.example.rickandmorty.presentation.characters.CharactersViewModel

class EpisodesFragment : Fragment(){

    private val viewModel: EpisodesViewModel by lazy {
        ViewModelProvider(this)[EpisodesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("EpisodesFragment", "Create View")
        return inflater.inflate(R.layout.fragment_episodes, container, false)
    }

    companion object {
        fun newInstance() = EpisodesFragment()
    }
}