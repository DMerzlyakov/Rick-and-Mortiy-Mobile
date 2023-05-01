package com.example.rickandmorty.location.presentation.list.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.location.presentation.list.model.LocationUiModel

class LocationsDiffUtil : DiffUtil.ItemCallback<LocationUiModel>() {
    override fun areItemsTheSame(oldItem: LocationUiModel, newItem: LocationUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocationUiModel, newItem: LocationUiModel): Boolean {
        return oldItem == newItem
    }
}