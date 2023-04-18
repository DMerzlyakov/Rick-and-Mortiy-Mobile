package com.example.rickandmorty.presentation.characters

import com.example.rickandmorty.domain.Character
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.presentation.OnClickRecyclerViewInterface
import com.example.rickandmorty.presentation.setImageFromUrl

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    onClickRecyclerViewInterface: OnClickRecyclerViewInterface<Character>
) : RecyclerView.ViewHolder(binding.root) {

    var mItem: Character? = null

    init {
        binding.root.setOnClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                mItem?.let { onClickRecyclerViewInterface.onItemClick(it, position) }
            }
        }
    }

    fun onBind(item: Character) {
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