package com.example.csapp

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
import com.example.csapp.data.Skin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SkinsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SkinAdapter
    private lateinit var progress: ProgressBar
    private lateinit var tvError: TextView
    private lateinit var etSearch: EditText

    private var allSkins: List<Skin> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skins)

        recyclerView = findViewById(R.id.rvSkins)
        progress = findViewById(R.id.progressSkins)
        tvError = findViewById(R.id.tvErrorSkins)
        etSearch = findViewById(R.id.etSearchSkins)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SkinAdapter(emptyList()) { skin ->
            android.widget.Toast.makeText(
                this,
                skin.name,
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }
        recyclerView.adapter = adapter

        carregarSkins()

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filtrarSkins(s?.toString() ?: "")
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun carregarSkins() {
        progress.visibility = View.VISIBLE
        tvError.visibility = View.GONE

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val skins = CsgoApiService.instance.getSkins()
                withContext(Dispatchers.Main) {
                    allSkins = skins
                    adapter.updateData(skins)
                    progress.visibility = View.GONE
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    progress.visibility = View.GONE
                    tvError.visibility = View.VISIBLE
                    tvError.text = "Erro ao carregar skins."
                }
            }
        }
    }

    private fun filtrarSkins(texto: String) {
        val query = texto.trim().lowercase()
        if (query.isEmpty()) {
            adapter.updateData(allSkins)
            return
        }

        val filtradas = allSkins.filter { skin ->
            val n = skin.name.lowercase()
            val w = skin.weapon?.name?.lowercase() ?: ""
            n.contains(query) || w.contains(query)
        }
        adapter.updateData(filtradas)
    }
}
