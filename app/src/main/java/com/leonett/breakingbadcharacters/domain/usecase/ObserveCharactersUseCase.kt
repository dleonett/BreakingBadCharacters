package com.leonett.breakingbadcharacters.domain.usecase

import com.leonett.breakingbadcharacters.data.CharactersRepository
import com.leonett.breakingbadcharacters.domain.model.CharacterDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObserveCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {

    fun invoke(): Flow<List<CharacterDomain>> =
        charactersRepository.observeCharacters().map { list ->
            list.map {
                CharacterDomain(
                    charId = it.charId,
                    name = it.name,
                    occupation = it.occupation,
                    img = it.img,
                    status = it.status,
                    nickname = it.nickname,
                    appearance = it.appearance,
                    portrayed = it.portrayed,
                    isFavorite = it.isFavorite
                )
            }
        }

}