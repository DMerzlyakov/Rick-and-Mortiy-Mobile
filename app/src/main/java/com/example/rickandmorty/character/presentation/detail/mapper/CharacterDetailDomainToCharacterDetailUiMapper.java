package com.example.rickandmorty.character.presentation.detail.mapper;

import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain;
import com.example.rickandmorty.character.presentation.detail.model.CharacterDetailUi;

import javax.inject.Inject;


public class CharacterDetailDomainToCharacterDetailUiMapper {

    @Inject
    public CharacterDetailDomainToCharacterDetailUiMapper(){}

    public CharacterDetailUi mapToCharacterDetailUi(CharacterDetailDomain item) {

        return new CharacterDetailUi(
                item.getId(),
                item.getName(),
                item.getStatus(),
                item.getSpecies(),
                item.getGender(),
                item.getOrigin(),
                item.getUrlAvatar(),
                item.getLocation(),
                item.getEpisodeIdList()
        );

    }
}
