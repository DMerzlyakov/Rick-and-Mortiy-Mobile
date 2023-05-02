package com.example.rickandmorty.character.presentation.list.recycler_view

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.character.presentation.list.model.CharacterUi
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.extention_util.OnClickRecyclerViewInterface
import com.example.rickandmorty.extention_util.setImageFromUrl

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    onClickRecyclerViewInterface: OnClickRecyclerViewInterface<CharacterUi>
) : RecyclerView.ViewHolder(binding.root) {

    var mItem: CharacterUi? = null

    init {
        binding.root.setOnClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                mItem?.let { onClickRecyclerViewInterface.onItemClick(it, position) }
            }
        }
    }

    fun onBind(item: CharacterUi) {
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