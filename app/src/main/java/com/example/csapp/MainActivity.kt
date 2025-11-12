package com.example.csapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSkins = findViewById<Button>(R.id.btnSkins)
        val btnStickers = findViewById<Button>(R.id.btnStickers)
        val btnHighlights = findViewById<Button>(R.id.btnHighlights)
        val btnCrates = findViewById<Button>(R.id.btnCrates)
        val btnAgents = findViewById<Button>(R.id.btnAgents)

        // 👉 AGORA ABRE A TELA DE SKINS
        btnSkins.setOnClickListener {
            val intent = Intent(this, SkinsActivity::class.java)
            startActivity(intent)
        }

        // Os outros ainda só mostram Toast por enquanto
        btnStickers.setOnClickListener {
            Toast.makeText(this, "Abrir lista de Stickers", Toast.LENGTH_SHORT).show()
        }

        btnHighlights.setOnClickListener {
            Toast.makeText(this, "Abrir lista de Highlights", Toast.LENGTH_SHORT).show()
        }

        btnCrates.setOnClickListener {
            Toast.makeText(this, "Abrir lista de Crates", Toast.LENGTH_SHORT).show()
        }

        btnAgents.setOnClickListener {
            Toast.makeText(this, "Abrir lista de Agents", Toast.LENGTH_SHORT).show()
        }
    }
}
