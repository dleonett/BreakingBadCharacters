package com.leonett.breakingbadcharacters.presentation.characters.list

import com.leonett.breakingbadcharacters.presentation.characters.model.CharacterUi

sealed class CharactersScreenState {
    data class Loading(val characters: List<CharacterUi>? = null) : CharactersScreenState()
    data class Success(val characters: List<CharacterUi>) : CharactersScreenState()
    data class Error(val characters: List<CharacterUi>? = null, val errorMessage: String) :
        CharactersScreenState()
}