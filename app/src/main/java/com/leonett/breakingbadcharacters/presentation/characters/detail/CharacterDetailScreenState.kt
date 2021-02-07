package com.leonett.breakingbadcharacters.presentation.characters.detail

import com.leonett.breakingbadcharacters.presentation.characters.model.CharacterUi

sealed class CharacterDetailScreenState {
    data class Success(val character: CharacterUi) : CharacterDetailScreenState()
}