package com.example.rickandmorty.universal_filter

import com.example.rickandmorty.location.domain.list.model.LocationFilter

interface OnFilterResultListenerLocation {
    fun confirmFilter(item: LocationFilter? = null)

}