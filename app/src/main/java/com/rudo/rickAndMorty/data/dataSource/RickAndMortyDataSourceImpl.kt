package com.rudo.rickAndMorty.data.dataSource

import com.rudo.rickAndMorty.data.dataSource.api.RickAndMortyApi
import com.rudo.rickAndMorty.data.dataSource.dto.CharacterDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RickAndMortyDataSourceImpl : RickAndMortyDataSource  {

    val httpLoginInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder().addInterceptor(httpLoginInterceptor).build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val api = retrofit.create(RickAndMortyApi::class.java)

    override suspend fun getCharacters(page: Int?): List<CharacterDto> {
        val response = api.getCharacters(page)

        return response.results
    }
}
