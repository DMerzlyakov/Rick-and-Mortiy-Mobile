package com.example.rickandmorty.presentation

import androidx.fragment.app.Fragment

interface OnNavigationListener {

    fun updateBottomNavigationVisibility(visibility: Int)

    fun navigateToFragment(fragment: Fragment)
}