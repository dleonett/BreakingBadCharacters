package com.leonett.breakingbadcharacters.presentation.characters.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterUi(
    val id: Long,
    val avatarUrl: String?,
    val name: String,
    val alias: String,
    val realName: String,
    val status: String,
    val occupation: List<String>?,
    val isFavorite: Boolean
) : Parcelable