package com.example.csapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.csapp.data.CsgoApiService
import com.example.csapp.data.Sticker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StickersActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StickerAdapter
    private lateinit var progress: ProgressBar
    private lateinit var tvError: TextView
    private lateinit var etSearch: EditText

    private var allStickers: List<Sticker> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stickers)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Stickers"

        recyclerView = findViewById(R.id.rvStickers)
        progress = findViewById(R.id.progressStickers)
        tvError = findViewById(R.id.tvErrorStickers)
        etSearch = findViewById(R.id.etSearchStickers)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StickerAdapter(emptyList()) { sticker ->
            val intent = Intent(this, StickerDetailActivity::class.java).apply {
                putExtra("name", sticker.name)
                putExtra("description", sticker.description ?: "")
                putExtra("image", sticker.image ?: "")
                putExtra("rarity", sticker.rarity?.name ?: "")
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        carregarStickers()

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filtrarStickers(s?.toString() ?: "")
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun carregarStickers() {
        progress.visibility = View.VISIBLE
        tvError.visibility = View.GONE

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val stickers = CsgoApiService.instance.getStickers()
                withContext(Dispatchers.Main) {
                    allStickers = stickers
                    adapter.updateData(stickers)
                    progress.visibility = View.GONE
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    progress.visibility = View.GONE
                    tvError.visibility = View.VISIBLE
                    tvError.text = "Erro ao carregar stickers."
                }
            }
        }
    }

    private fun filtrarStickers(texto: String) {
        val query = texto.trim().lowercase()
        if (query.isEmpty()) {
            adapter.updateData(allStickers)
            return
        }

        val filtradas = allStickers.filter { s ->
            val name = s.name.lowercase()
            val rarity = s.rarity?.name?.lowercase() ?: ""
            name.contains(query) || rarity.contains(query)
        }

        adapter.updateData(filtradas)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
