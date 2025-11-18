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
import com.example.csapp.data.Crate
import com.example.csapp.data.CsgoApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CratesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CrateAdapter
    private lateinit var progress: ProgressBar
    private lateinit var tvError: TextView
    private lateinit var etSearch: EditText

    private var allCrates: List<Crate> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crates)

        recyclerView = findViewById(R.id.rvCrates)
        progress = findViewById(R.id.progressCrates)
        tvError = findViewById(R.id.tvErrorCrates)
        etSearch = findViewById(R.id.etSearchCrates)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CrateAdapter(emptyList()) { crate ->
            val intent = Intent(this, CrateDetailActivity::class.java).apply {
                putExtra("name", crate.name)
                putExtra("description", crate.description ?: "")
                putExtra("type", crate.type ?: "")
                putExtra("date", crate.first_sale_date ?: "")
                putExtra("image", crate.image ?: "")
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        carregarCrates()

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filtrarCrates(s?.toString() ?: "")
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun carregarCrates() {
        progress.visibility = View.VISIBLE
        tvError.visibility = View.GONE

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val crates = CsgoApiService.instance.getCrates()
                withContext(Dispatchers.Main) {
                    allCrates = crates
                    adapter.updateData(crates)
                    progress.visibility = View.GONE
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    progress.visibility = View.GONE
                    tvError.visibility = View.VISIBLE
                    tvError.text = "Erro ao carregar crates."
                }
            }
        }
    }

    private fun filtrarCrates(texto: String) {
        val query = texto.trim().lowercase()
        if (query.isEmpty()) {
            adapter.updateData(allCrates)
            return
        }

        val filtradas = allCrates.filter { c ->
            val name = c.name.lowercase()
            val type = c.type?.lowercase() ?: ""
            name.contains(query) || type.contains(query)
        }

        adapter.updateData(filtradas)
    }
}
