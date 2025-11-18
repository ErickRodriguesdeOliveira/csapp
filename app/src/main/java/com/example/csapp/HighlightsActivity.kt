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
import com.example.csapp.data.Highlight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HighlightsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HighlightAdapter
    private lateinit var progress: ProgressBar
    private lateinit var tvError: TextView
    private lateinit var etSearch: EditText

    private var allHighlights: List<Highlight> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highlights)

        recyclerView = findViewById(R.id.rvHighlights)
        progress = findViewById(R.id.progressHighlights)
        tvError = findViewById(R.id.tvErrorHighlights)
        etSearch = findViewById(R.id.etSearchHighlights)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = HighlightAdapter(emptyList()) { highlight ->
            // Abrir tela de detalhes
            val intent = Intent(this, HighlightDetailActivity::class.java).apply {
                putExtra("name", highlight.name)
                putExtra("description", highlight.description ?: "")
                putExtra("image", highlight.image ?: "")
                putExtra("video", highlight.video ?: "")
                putExtra("event", highlight.tournament_event ?: "")
                putExtra("team0", highlight.team0 ?: "")
                putExtra("team1", highlight.team1 ?: "")
                putExtra("map", highlight.map ?: "")
                putExtra("stage", highlight.stage ?: "")
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        carregarHighlights()

        // Filtro de busca (8)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filtrarHighlights(s?.toString() ?: "")
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun carregarHighlights() {
        progress.visibility = View.VISIBLE
        tvError.visibility = View.GONE

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val highlights = CsgoApiService.instance.getHighlights()
                withContext(Dispatchers.Main) {
                    allHighlights = highlights
                    adapter.updateData(highlights)
                    progress.visibility = View.GONE
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    progress.visibility = View.GONE
                    tvError.visibility = View.VISIBLE
                    tvError.text = "Erro ao carregar highlights."
                }
            }
        }
    }

    private fun filtrarHighlights(texto: String) {
        val query = texto.trim().lowercase()
        if (query.isEmpty()) {
            adapter.updateData(allHighlights)
            return
        }

        val filtradas = allHighlights.filter { h ->
            val name = h.name.lowercase()
            val event = h.tournament_event?.lowercase() ?: ""
            val t0 = h.team0?.lowercase() ?: ""
            val t1 = h.team1?.lowercase() ?: ""
            name.contains(query) || event.contains(query) || t0.contains(query) || t1.contains(query)
        }

        adapter.updateData(filtradas)
    }
}
