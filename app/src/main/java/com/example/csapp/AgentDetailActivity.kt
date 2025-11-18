package com.example.csapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load

class AgentDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_detail)

        val img = findViewById<ImageView>(R.id.imgAgentDetail)
        val tvName = findViewById<TextView>(R.id.tvAgentNameDetail)
        val tvRarity = findViewById<TextView>(R.id.tvAgentRarityDetail)
        val tvDesc = findViewById<TextView>(R.id.tvAgentDescDetail)

        val name = intent.getStringExtra("name") ?: ""
        val desc = intent.getStringExtra("description") ?: ""
        val rarity = intent.getStringExtra("rarity") ?: ""
        val image = intent.getStringExtra("image") ?: ""

        tvName.text = name
        tvRarity.text = if (rarity.isNotBlank()) "Raridade: $rarity" else "Raridade desconhecida"
        tvDesc.text = if (desc.isNotBlank()) desc else "Sem descrição."

        img.load(image) {
            crossfade(true)
        }
    }
}
