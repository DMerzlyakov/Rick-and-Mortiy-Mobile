package com.example.rickandmorty.location.presentation.list.recycler_view

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemLocationBinding
import com.example.rickandmorty.utils.OnClickRecyclerViewInterface
import com.example.rickandmorty.location.presentation.list.model.LocationUi

class LocationsViewHolder(
    private val binding: ItemLocationBinding,
    onClickRecyclerViewInterface: OnClickRecyclerViewInterface<LocationUi>
) : RecyclerView.ViewHolder(binding.root) {

    var mItem: LocationUi? = null

    init {
        binding.root.setOnClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                mItem?.let { onClickRecyclerViewInterface.onItemClick(it, position) }
            }
        }
    }

    fun onBind(item: LocationUi) {
        with(binding) {
            nameView.text = item.name
            dimensionView.text = item.dimension
            typeView.text = item.type
        }

        mItem = item

    }

}