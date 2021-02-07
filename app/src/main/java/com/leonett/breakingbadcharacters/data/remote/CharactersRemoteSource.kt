package com.leonett.breakingbadcharacters.data.remote

import com.leonett.breakingbadcharacters.data.remote.model.CharacterRemote
import retrofit2.http.GET
import javax.inject.Inject

class CharactersRemoteSource @Inject constructor(private val charactersApiService: CharactersApiService) {

    suspend fun fetchCharacters() = charactersApiService.fetchCharacters()

}

interface CharactersApiService {

    @GET("api/characters?limit=100")
    suspend fun fetchCharacters(): List<CharacterRemote>

}