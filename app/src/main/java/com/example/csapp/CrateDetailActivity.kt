package com.example.csapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load

class CrateDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crate_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detalhes da Crate"

        val imgCrate = findViewById<ImageView>(R.id.imgCrateDetail)
        val tvName = findViewById<TextView>(R.id.tvCrateDetailName)
        val tvType = findViewById<TextView>(R.id.tvCrateDetailType)
        val tvDate = findViewById<TextView>(R.id.tvCrateDetailDate)
        val tvDescription = findViewById<TextView>(R.id.tvCrateDetailDescription)

        val name = intent.getStringExtra("name") ?: ""
        val type = intent.getStringExtra("type") ?: "Tipo desconhecido"
        val date = intent.getStringExtra("date") ?: "Sem data"
        val description = intent.getStringExtra("description") ?: ""
        val image = intent.getStringExtra("image") ?: ""

        tvName.text = name
        tvType.text = "Tipo: $type"
        tvDate.text = "Primeira venda: $date"
        tvDescription.text = description

        imgCrate.load(image)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
