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
import com.example.csapp.data.Agent
import com.example.csapp.data.CsgoApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AgentsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AgentAdapter
    private lateinit var progress: ProgressBar
    private lateinit var tvError: TextView
    private lateinit var etSearch: EditText

    private var allAgents: List<Agent> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agents)

        recyclerView = findViewById(R.id.rvAgents)
        progress = findViewById(R.id.progressAgents)
        tvError = findViewById(R.id.tvErrorAgents)
        etSearch = findViewById(R.id.etSearchAgents)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AgentAdapter(emptyList()) { agent ->
            val intent = Intent(this, AgentDetailActivity::class.java).apply {
                putExtra("name", agent.name)
                putExtra("description", agent.description ?: "")
                putExtra("rarity", agent.rarity?.name ?: "")
                putExtra("image", agent.image ?: "")
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        carregarAgents()

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filtrarAgents(s?.toString() ?: "")
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun carregarAgents() {
        progress.visibility = View.VISIBLE
        tvError.visibility = View.GONE

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val agents = CsgoApiService.instance.getAgents()
                withContext(Dispatchers.Main) {
                    allAgents = agents
                    adapter.updateData(agents)
                    progress.visibility = View.GONE
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    progress.visibility = View.GONE
                    tvError.visibility = View.VISIBLE
                    tvError.text = "Erro ao carregar agents."
                }
            }
        }
    }

    private fun filtrarAgents(texto: String) {
        val query = texto.trim().lowercase()
        if (query.isEmpty()) {
            adapter.updateData(allAgents)
            return
        }

        val filtrados = allAgents.filter { a ->
            val name = a.name.lowercase()
            val rarity = a.rarity?.name?.lowercase() ?: ""   // <-- aqui é o ajuste
            name.contains(query) || rarity.contains(query)
        }

        adapter.updateData(filtrados)
    }
}
