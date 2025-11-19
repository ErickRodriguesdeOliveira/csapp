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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detalhes do Agent"

        val imgAgent = findViewById<ImageView>(R.id.imgAgentDetail)
        val tvName = findViewById<TextView>(R.id.tvAgentDetailName)
        val tvRarity = findViewById<TextView>(R.id.tvAgentDetailRarity)
        val tvDescription = findViewById<TextView>(R.id.tvAgentDetailDescription)

        val name = intent.getStringExtra("name") ?: ""
        val rarity = intent.getStringExtra("rarity") ?: ""
        val description = intent.getStringExtra("description") ?: ""
        val image = intent.getStringExtra("image") ?: ""

        tvName.text = name
        tvRarity.text = "Raridade: $rarity"
        tvDescription.text = description

        imgAgent.load(image)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
