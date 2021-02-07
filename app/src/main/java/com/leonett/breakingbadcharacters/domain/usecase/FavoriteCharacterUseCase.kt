package com.leonett.breakingbadcharacters.domain.usecase

import com.leonett.breakingbadcharacters.data.CharactersRepository
import javax.inject.Inject

class FavoriteCharacterUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {

    suspend fun invoke(charId: Long, isFavorite: Boolean) =
        charactersRepository.updateCharacterAsFavorite(charId, isFavorite)

}