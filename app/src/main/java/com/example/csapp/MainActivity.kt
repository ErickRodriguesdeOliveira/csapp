package com.example.csapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cardSkins = findViewById<MaterialCardView>(R.id.cardSkins)
        val cardStickers = findViewById<MaterialCardView>(R.id.cardStickers)
        val cardHighlights = findViewById<MaterialCardView>(R.id.cardHighlights)
        val cardCrates = findViewById<MaterialCardView>(R.id.cardCrates)
        val cardAgents = findViewById<MaterialCardView>(R.id.cardAgents)

        cardSkins.setOnClickListener {
            val intent = Intent(this, SkinsActivity::class.java)
            startActivity(intent)
        }

        cardStickers.setOnClickListener {
            val intent = Intent(this, StickersActivity::class.java)
            startActivity(intent)
        }

        cardHighlights.setOnClickListener {
            val intent = Intent(this, HighlightsActivity::class.java)
            startActivity(intent)
        }

        cardCrates.setOnClickListener {
            val intent = Intent(this, CratesActivity::class.java)
            startActivity(intent)
        }

        cardAgents.setOnClickListener {
            val intent = Intent(this, AgentsActivity::class.java)
            startActivity(intent)
        }
    }
}
