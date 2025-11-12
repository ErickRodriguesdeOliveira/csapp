package com.example.csapp

import com.example.csapp.data.CsgoApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class CsgoApiTest {

    @Test
    fun `getSkins deve retornar uma lista com itens`() = runBlocking {
        val skins = CsgoApiService.instance.getSkins()

        println("Total de skins recebidas: ${skins.size}")
        println("Primeira skin: ${skins.firstOrNull()?.name}")

        assertTrue("A lista de skins deveria ter itens", skins.isNotEmpty())
    }
}
