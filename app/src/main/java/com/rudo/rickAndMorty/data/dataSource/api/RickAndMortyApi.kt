package com.rudo.rickAndMorty.data.dataSource.api

import com.rudo.rickAndMorty.data.dataSource.dto.CharactersResponseDto // Make sure to import your DTO
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int?): CharactersResponseDto

}
