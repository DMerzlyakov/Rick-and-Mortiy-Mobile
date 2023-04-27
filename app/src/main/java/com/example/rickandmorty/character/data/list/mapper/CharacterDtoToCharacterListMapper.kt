package com.example.rickandmorty.character.data.list.mapper

import com.example.rickandmorty.character.data.list.remote.dto.CharactersDTO
import com.example.rickandmorty.character.domain.list.model.CharacterDomain

class CharacterDtoToCharacterListMapper {

    operator fun invoke(listItem: CharactersDTO): List<CharacterDomain> {
        return  listItem.results.map {
            CharacterDomain(it.id, it.name, it.status, it.species, it.gender, it.image)
        }


    }
}