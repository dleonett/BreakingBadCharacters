package com.leonett.breakingbadcharacters.data

import com.leonett.breakingbadcharacters.data.local.CharactersLocalSource
import com.leonett.breakingbadcharacters.data.local.model.CharacterEntity
import com.leonett.breakingbadcharacters.data.remote.CharactersRemoteSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val remoteSource: CharactersRemoteSource,
    private val localSource: CharactersLocalSource
) {

    suspend fun fetchCharacters() = remoteSource.fetchCharacters()

    suspend fun updateCharacterAsFavorite(charId: Long, isFavorite: Boolean) =
        localSource.updateCharacterAsFavorite(charId, isFavorite)

    suspend fun insertCharacters(characters: List<CharacterEntity>) =
        localSource.insertAll(characters)

    fun observeCharacters(): Flow<List<CharacterEntity>> = localSource.getAll()

    fun observeCharacter(charId: Long): Flow<CharacterEntity> = localSource.get(charId)

}

