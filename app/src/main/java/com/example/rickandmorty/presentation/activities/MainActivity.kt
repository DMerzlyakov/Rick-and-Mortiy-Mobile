package com.example.rickandmorty.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.rickandmorty.R
import com.example.rickandmorty.data.remote.CharacterApi
import com.example.rickandmorty.data.remote.RetrofitClient
import com.example.rickandmorty.data.repository.RepositoryImpl
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.databinding.ActivitySplashScreenBinding
import com.example.rickandmorty.presentation.OnNavigationListener
import com.example.rickandmorty.presentation.characters.CharactersFragment
import com.example.rickandmorty.presentation.episodes.EpisodesFragment
import com.example.rickandmorty.presentation.locations.LocationsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnNavigationListener {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(
            layoutInflater
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAnimationOnCreate()
        setContentView(binding.root)

        setupBottomNavigationListener()
    }


    private fun setAnimationOnCreate() =
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

    override fun updateBottomNavigationVisibility(visibility: Int) {
        binding.navView.visibility = visibility
    }

    override fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun toBackStack() {
        supportFragmentManager.popBackStack()
    }

    private fun setupBottomNavigationListener() {
        binding.navView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.characters -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, CharactersFragment.newInstance())
                        .commit()
                    true
                }
                R.id.locations -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, LocationsFragment.newInstance())
                        .commit()
                    true
                }
                R.id.episodes -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, EpisodesFragment.newInstance())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}