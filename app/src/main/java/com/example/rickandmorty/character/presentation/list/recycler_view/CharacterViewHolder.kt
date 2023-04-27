package com.example.rickandmorty.character.presentation.list.recycler_view

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import com.example.rickandmorty.character.presentation.list.model.CharacterItem
import com.example.rickandmorty.extention_util.setImageFromUrl
import com.example.rickandmorty.databinding.ItemCharacterBinding

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    onClickRecyclerViewInterface: OnClickRecyclerViewInterface<CharacterItem>
) : RecyclerView.ViewHolder(binding.root) {

    var mItem: CharacterItem? = null

    init {
        binding.root.setOnClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                mItem?.let { onClickRecyclerViewInterface.onItemClick(it, position) }
            }
        }
    }

    fun onBind(item: CharacterItem) {
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