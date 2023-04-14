package com.example.rickandmorty.presentation.locations

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

class LocationsFragment : Fragment(){


    private val viewModel: LocationsViewModel by lazy {
        ViewModelProvider(this)[LocationsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("LocationsFragment", "Create View")
        return inflater.inflate(R.layout.fragment_locations, container, false)
    }


    companion object {
        fun newInstance() = LocationsFragment()
    }

}