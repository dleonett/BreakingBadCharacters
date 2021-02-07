package com.leonett.breakingbadcharacters.domain.model

data class CharacterDomain(
    val charId: Long,
    val name: String,
    val occupation: List<String>,
    val img: String,
    val status: String,
    val nickname: String,
    val portrayed: String,
    val appearance: List<Int>?,
    val isFavorite: Boolean
)
