package com.example.csapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load

class SkinDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skin_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detalhes da Skin"

        val imgSkin = findViewById<ImageView>(R.id.imgSkinDetail)
        val tvName = findViewById<TextView>(R.id.tvSkinDetailName)
        val tvWeapon = findViewById<TextView>(R.id.tvSkinDetailWeapon)
        val tvRarity = findViewById<TextView>(R.id.tvSkinDetailRarity)

        val name = intent.getStringExtra("name") ?: ""
        val weapon = intent.getStringExtra("weapon") ?: ""
        val rarity = intent.getStringExtra("rarity") ?: ""
        val image = intent.getStringExtra("image") ?: ""

        tvName.text = name
        tvWeapon.text = weapon
        tvRarity.text = rarity

        imgSkin.load(image)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
