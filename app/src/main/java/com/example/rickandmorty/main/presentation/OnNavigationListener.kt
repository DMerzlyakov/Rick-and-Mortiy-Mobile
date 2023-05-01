package com.example.rickandmorty.main.presentation

import androidx.fragment.app.Fragment

interface OnNavigationListener {

    fun updateBottomNavigationVisibility(visibility: Int)

    fun navigateToFragment(fragment: Fragment)

    fun toBackStack()
}