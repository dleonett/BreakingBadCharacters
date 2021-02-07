package com.leonett.breakingbadcharacters.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leonett.breakingbadcharacters.data.local.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("SELECT * FROM characters ORDER BY isFavorite DESC")
    fun getAll(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM characters WHERE charId = :charId")
    fun get(charId: Long): Flow<CharacterEntity>

    @Query("UPDATE characters SET isFavorite = :isFavorite WHERE charId = :charId")
    suspend fun updateCharacterAsFavorite(charId: Long, isFavorite: Boolean)
}