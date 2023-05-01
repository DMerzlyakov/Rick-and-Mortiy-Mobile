package com.example.rickandmorty.location.presentation.list.recycler_view

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemLocationBinding
import com.example.rickandmorty.extention_util.OnClickRecyclerViewInterface
import com.example.rickandmorty.location.presentation.list.model.LocationUiModel

class LocationsViewHolder(
    private val binding: ItemLocationBinding,
    onClickRecyclerViewInterface: OnClickRecyclerViewInterface<LocationUiModel>
) : RecyclerView.ViewHolder(binding.root) {

    var mItem: LocationUiModel? = null

    init {
        binding.root.setOnClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                mItem?.let { onClickRecyclerViewInterface.onItemClick(it, position) }
            }
        }
    }

    fun onBind(item: LocationUiModel) {
        with(binding) {
            nameView.text = item.name
            dimensionView.text = item.dimension
            typeView.text = item.type
        }

        mItem = item

    }

}