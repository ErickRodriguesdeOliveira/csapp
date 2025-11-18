package com.example.csapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Base da API do ByMykel (em inglês)
private const val BASE_URL =
    "https://raw.githubusercontent.com/ByMykel/CSGO-API/main/public/api/en/"

// ---------- SKINS ----------

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

// ---------- HIGHLIGHTS ----------

data class Highlight(
    val id: String,
    val name: String,
    val description: String?,
    val tournament_event: String?,
    val team0: String?,
    val team1: String?,
    val stage: String?,
    val map: String?,
    val market_hash_name: String?,
    val image: String?,
    val video: String?
)

// ---------- CRATES ----------

data class Crate(
    val id: String,
    val name: String,
    val description: String?,
    val type: String?,
    val first_sale_date: String?,
    val image: String?
)

// ---------- AGENTS ----------
// De acordo com a doc: id, name, description, rarity, image
data class Agent(
    val id: String,
    val name: String,
    val description: String?,
    val rarity: Rarity?,
    val image: String?
)

// ---------- INTERFACE RETROFIT ----------

interface CsgoApi {

    // Skins
    @GET("skins.json")
    suspend fun getSkins(): List<Skin>

    // Highlights
    @GET("highlights.json")
    suspend fun getHighlights(): List<Highlight>

    // Crates
    @GET("crates.json")
    suspend fun getCrates(): List<Crate>

    // Agents
    @GET("agents.json")
    suspend fun getAgents(): List<Agent>
}

// ---------- SINGLETON DO RETROFIT ----------

object CsgoApiService {
    val instance: CsgoApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CsgoApi::class.java)
    }
}
