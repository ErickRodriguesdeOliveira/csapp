package com.example.csapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load

class StickerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sticker_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detalhes do Sticker"

        val imgSticker = findViewById<ImageView>(R.id.imgStickerDetail)
        val tvName = findViewById<TextView>(R.id.tvStickerDetailName)
        val tvRarity = findViewById<TextView>(R.id.tvStickerDetailRarity)
        val tvDescription = findViewById<TextView>(R.id.tvStickerDetailDescription)

        val name = intent.getStringExtra("name") ?: ""
        val description = intent.getStringExtra("description") ?: "Sem descrição."
        val image = intent.getStringExtra("image") ?: ""
        val rarity = intent.getStringExtra("rarity") ?: "Raridade desconhecida"

        tvName.text = name
        tvRarity.text = "Raridade: $rarity"
        tvDescription.text = description

        imgSticker.load(image) {
            crossfade(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
