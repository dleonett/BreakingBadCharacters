package com.leonett.breakingbadcharacters.data.local

import com.leonett.breakingbadcharacters.data.local.db.CharactersDatabase
import com.leonett.breakingbadcharacters.data.local.model.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersLocalSource @Inject constructor(private val database: CharactersDatabase) {

    suspend fun updateCharacterAsFavorite(charId: Long, isFavorite: Boolean) =
        database.charactersDao().updateCharacterAsFavorite(charId, isFavorite)

    suspend fun insertAll(characters: List<CharacterEntity>) =
        database.charactersDao().insertAll(characters)

    fun getAll(): Flow<List<CharacterEntity>> = database.charactersDao().getAll()

    fun get(charId: Long): Flow<CharacterEntity> = database.charactersDao().get(charId)

}
