package com.leonett.breakingbadcharacters.presentation.characters.list

import com.airbnb.epoxy.Typed2EpoxyController
import com.leonett.breakingbadcharacters.presentation.adapter.character
import com.leonett.breakingbadcharacters.presentation.adapter.loader
import com.leonett.breakingbadcharacters.presentation.characters.model.CharacterUi

class CharactersEpoxyController(private val onInteractionListener: OnInteractionListener? = null) :
    Typed2EpoxyController<List<CharacterUi>, Boolean>() {

    override fun buildModels(characters: List<CharacterUi>?, isLoading: Boolean) {
        characters?.let {
            it.forEach { character ->
                character {
                    id("$CHARACTER_ID ${character.id}")
                    character(character)
                    onItemClickListener { onInteractionListener?.onCharacterItemClick(character) }
                    onLikeClickListener { onInteractionListener?.onCharacterLikeClick(character) }
                }
            }
        }

        if (isLoading) {
            loader {
                id(LOADER_ID)
            }
        }
    }

    interface OnInteractionListener {
        fun onCharacterItemClick(characterUi: CharacterUi)
        fun onCharacterLikeClick(characterUi: CharacterUi)
    }

    companion object {
        private const val CHARACTER_ID = "CHARACTER_ID"
        private const val LOADER_ID = "LOADER_ID"
    }

}