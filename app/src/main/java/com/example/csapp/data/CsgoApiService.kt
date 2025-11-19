package com.example.csapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


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

data class Highlight(
    val id: String,
    val name: String,
    val description: String?,
    val tournament_event: String?,
    val team0: String?,
    val team1: String?,
    val stage: String?,
    val map: String?,
    val image: String?,
    val video: String?
)

data class Crate(
    val id: String,
    val name: String,
    val description: String?,
    val type: String?,
    val first_sale_date: String?,
    val image: String?
)


data class Agent(
    val id: String,
    val name: String,
    val description: String?,
    val rarity: Rarity?,
    val image: String?
)

data class Sticker(
    val id: String,
    val name: String,
    val description: String?,
    val rarity: Rarity?,
    val image: String?
)

// ---------- INTERFACE RETROFIT ----------

interface CsgoApi {
    @GET("skins.json")
    suspend fun getSkins(): List<Skin>

    @GET("highlights.json")
    suspend fun getHighlights(): List<Highlight>

    @GET("crates.json")
    suspend fun getCrates(): List<Crate>

    @GET("agents.json")
    suspend fun getAgents(): List<Agent>

    @GET("stickers.json")
    suspend fun getStickers(): List<Sticker>
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
