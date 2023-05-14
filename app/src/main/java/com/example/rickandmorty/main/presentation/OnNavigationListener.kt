package com.example.rickandmorty.main.presentation


interface OnNavigationListener {

    fun setupBottomNavigationVisible()

    fun navigateToCharacterDetailFragment(id:Int)
    fun navigateToEpisodeDetailFragment(id: Int)
    fun navigateToLocationDetailFragment(id: Int)

    fun toBackStack()
}