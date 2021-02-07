package com.leonett.breakingbadcharacters.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    val charId: Long,
    val name: String,
    val occupation: List<String>,
    val img: String,
    val status: String,
    val nickname: String,
    val appearance: List<Int>?,
    val portrayed: String,
    val isFavorite: Boolean
)
