package com.example.rickandmorty.location.presentation.list.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.location.presentation.list.model.LocationUi

class LocationsDiffUtil : DiffUtil.ItemCallback<LocationUi>() {
    override fun areItemsTheSame(oldItem: LocationUi, newItem: LocationUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocationUi, newItem: LocationUi): Boolean {
        return oldItem == newItem
    }
}