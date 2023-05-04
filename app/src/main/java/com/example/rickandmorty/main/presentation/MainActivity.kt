package com.example.rickandmorty.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rickandmorty.R
import com.example.rickandmorty.character.presentation.list.CharacterListFragment
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.episode.presentation.list.EpisodeListFragment
import com.example.rickandmorty.location.presentation.list.LocationListFragment

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
                        .replace(
                            R.id.fragment_container,
                            CharacterListFragment.newInstance(CharacterListFragment.TypeFullScreen)
                        )
                        .commit()
                    true
                }
                R.id.locations -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, LocationListFragment.newInstance())
                        .commit()
                    true
                }
                R.id.episodes -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container,
                            EpisodeListFragment.newInstance(EpisodeListFragment.TypeFullScreen)
                        )
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}