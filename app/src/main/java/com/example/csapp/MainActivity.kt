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

        btnSkins.setOnClickListener {
            val intent = Intent(this, SkinsActivity::class.java)
            startActivity(intent)
        }

        btnStickers.setOnClickListener {
            Toast.makeText(this, "Abrir lista de Stickers", Toast.LENGTH_SHORT).show()
        }

        btnHighlights.setOnClickListener {
            val intent = Intent(this, HighlightsActivity::class.java)
            startActivity(intent)
        }

        btnCrates.setOnClickListener {
            val intent = Intent(this, CratesActivity::class.java)
            startActivity(intent)
        }

        btnAgents.setOnClickListener {
            val intent = Intent(this, AgentsActivity::class.java)
            startActivity(intent)
        }
    }
}
