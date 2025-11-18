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

        val img = findViewById<ImageView>(R.id.imgCrateDetail)
        val tvName = findViewById<TextView>(R.id.tvCrateNameDetail)
        val tvInfo = findViewById<TextView>(R.id.tvCrateInfoDetail)
        val tvDesc = findViewById<TextView>(R.id.tvCrateDescDetail)

        val name = intent.getStringExtra("name") ?: ""
        val desc = intent.getStringExtra("description") ?: ""
        val type = intent.getStringExtra("type") ?: ""
        val date = intent.getStringExtra("date") ?: ""
        val image = intent.getStringExtra("image") ?: ""

        tvName.text = name
        tvInfo.text = "Tipo: $type\nPrimeira venda: $date"
        tvDesc.text = if (desc.isNotBlank()) desc else "Sem descrição."

        img.load(image) {
            crossfade(true)
        }
    }
}
