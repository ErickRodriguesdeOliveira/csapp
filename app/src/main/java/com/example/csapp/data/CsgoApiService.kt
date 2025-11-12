package com.example.csapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Base da API do ByMykel (em inglês)
private const val BASE_URL =
    "https://raw.githubusercontent.com/ByMykel/CSGO-API/main/public/api/en/"

data class Skin(
    val id: String,
    val name: String,
    val weapon: Weapon?,
    val rarity: Rarity?,
    val image: String?
)

data class Weapon(
    val name: String?
)

data class Rarity(
    val name: String?
)

interface CsgoApi {
    @GET("skins.json")
    suspend fun getSkins(): List<Skin>
}

object CsgoApiService {
    val instance: CsgoApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CsgoApi::class.java)
    }
}
