package com.rudo.rickAndMorty.data.dataSource

import com.rudo.rickAndMorty.data.dataSource.api.RickAndMortyApi
import com.rudo.rickAndMorty.data.dataSource.dto.CharacterDetailDto
import com.rudo.rickAndMorty.data.dataSource.dto.CharacterDto
import com.rudo.rickAndMorty.data.dataSource.dto.CharactersResponseDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RickAndMortyDataSourceImpl @Inject constructor() : RickAndMortyDataSource {

    private val httpLoginInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    
    private val client = OkHttpClient.Builder()
        .addInterceptor(httpLoginInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val api = retrofit.create(RickAndMortyApi::class.java)

    override suspend fun getCharacters(page: Int?): List<CharacterDto> {
        return api.getCharacters(page).results
    }

    override suspend fun getCharacterById(id: Int): CharacterDetailDto {
        return api.getCharacterById(id)
    }
    
    override suspend fun searchCharacters(query: String): CharactersResponseDto {
        return api.searchCharacters(query)
    }
}
