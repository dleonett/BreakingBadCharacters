package com.leonett.breakingbadcharacters.domain.usecase

import com.leonett.breakingbadcharacters.data.CharactersRepository
import com.leonett.breakingbadcharacters.data.local.model.CharacterEntity
import javax.inject.Inject

class FetchCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {

    suspend fun invoke() {
        val result = charactersRepository.fetchCharacters().map {
            CharacterEntity(
                charId = it.charId,
                name = it.name,
                occupation = it.occupation,
                img = it.img,
                status = it.status,
                nickname = it.nickname,
                appearance = it.appearance,
                portrayed = it.portrayed,
                isFavorite = false
            )
        }
        charactersRepository.insertCharacters(result)
    }

}