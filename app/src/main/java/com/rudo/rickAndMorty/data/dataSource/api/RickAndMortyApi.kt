package com.rudo.rickAndMorty.data.dataSource.api

import com.rudo.rickAndMorty.data.dataSource.dto.CharacterDetailDto
import com.rudo.rickAndMorty.data.dataSource.dto.CharactersResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("api/character")
    suspend fun getCharacters(@Query("page") page: Int?): CharactersResponseDto

    @GET("api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterDetailDto
    
    @GET("api/character/")
    suspend fun searchCharacters(
        @Query("name") query: String,
    ): CharactersResponseDto
}
