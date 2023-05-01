package com.example.rickandmorty.location.presentation.list.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.rickandmorty.databinding.ItemLocationBinding
import com.example.rickandmorty.extention_util.OnClickRecyclerViewInterface
import com.example.rickandmorty.location.presentation.list.model.LocationUiModel

class LocationsRecyclerViewAdapter(private val onClickRecyclerViewInterface: OnClickRecyclerViewInterface<LocationUiModel>) :
    PagingDataAdapter<LocationUiModel, LocationsViewHolder>(LocationsDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        return LocationsViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickRecyclerViewInterface
        )
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.onBind(getItem(position) ?: return)
    }
}