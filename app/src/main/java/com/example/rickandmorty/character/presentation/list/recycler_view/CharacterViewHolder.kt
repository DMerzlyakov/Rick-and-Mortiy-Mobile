package com.example.rickandmorty.character.presentation.list.recycler_view

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.character.presentation.list.model.CharacterUiModel
import com.example.rickandmorty.extention_util.setImageFromUrl
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.extention_util.OnClickRecyclerViewInterface

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    onClickRecyclerViewInterface: OnClickRecyclerViewInterface<CharacterUiModel>
) : RecyclerView.ViewHolder(binding.root) {

    var mItem: CharacterUiModel? = null

    init {
        binding.root.setOnClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                mItem?.let { onClickRecyclerViewInterface.onItemClick(it, position) }
            }
        }
    }

    fun onBind(item: CharacterUiModel) {
        with(binding) {
            nameView.text = item.name
            genreView.text = item.gender
            speciesView.text = item.species
            statusView.text = item.status

            avatarView.setImageFromUrl(item.urlAvatar, binding.root.context)
        }

        mItem = item

    }

}