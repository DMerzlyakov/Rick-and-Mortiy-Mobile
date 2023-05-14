package com.example.rickandmorty.main.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rickandmorty.R
import com.example.rickandmorty.character.presentation.detail.CharacterDetailsFragment
import com.example.rickandmorty.character.presentation.list.CharacterListFragment
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.episode.presentation.detail.EpisodeDetailsFragment
import com.example.rickandmorty.episode.presentation.list.EpisodeListFragment
import com.example.rickandmorty.location.presentation.detail.LocationDetailFragment
import com.example.rickandmorty.location.presentation.list.LocationListFragment

class MainActivity : AppCompatActivity(), OnNavigationListener {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAnimationOnCreate()
        setContentView(binding.root)
        setupBottomNavigationListener()
    }

    private fun setAnimationOnCreate() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun setupBottomNavigationVisible() = with(binding) {
        navView.visibility = View.VISIBLE
    }

    override fun navigateToCharacterDetailFragment(id: Int) {
        binding.navView.visibility = View.GONE
        val fragment = CharacterDetailsFragment.newInstance(id)
        replaceFragmentWithBackStack(fragment)
    }

    override fun navigateToEpisodeDetailFragment(id: Int) {
        binding.navView.visibility = View.GONE
        val fragment = EpisodeDetailsFragment.newInstance(id)
        replaceFragmentWithBackStack(fragment)
    }

    override fun navigateToLocationDetailFragment(id: Int) {
        binding.navView.visibility = View.GONE
        val fragment = LocationDetailFragment.newInstance(id)
        replaceFragmentWithBackStack(fragment)
    }

    override fun toBackStack() {
        supportFragmentManager.popBackStack()
    }

    private fun replaceFragmentWithBackStack(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }


    private fun setupBottomNavigationListener() {
        binding.navView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.characters -> {
                    replaceFragment(CharacterListFragment.newInstance())
                    true
                }
                R.id.locations -> {
                    replaceFragment(LocationListFragment.newInstance())
                    true
                }
                R.id.episodes -> {
                    replaceFragment(EpisodeListFragment.newInstance())
                    true
                }
                else -> false
            }
        }
    }
}