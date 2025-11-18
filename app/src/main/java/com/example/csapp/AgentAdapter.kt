package com.example.csapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.csapp.data.Agent

class AgentAdapter(
    private var items: List<Agent>,
    private val onClick: (Agent) -> Unit
) : RecyclerView.Adapter<AgentAdapter.AgentViewHolder>() {

    fun updateData(newItems: List<Agent>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class AgentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgAgent: ImageView = itemView.findViewById(R.id.imgAgent)
        private val tvName: TextView = itemView.findViewById(R.id.tvAgentName)
        private val tvInfo: TextView = itemView.findViewById(R.id.tvAgentInfo)

        fun bind(agent: Agent) {
            tvName.text = agent.name

            val rarityName = agent.rarity?.name ?: "Raridade desconhecida"
            tvInfo.text = rarityName

            imgAgent.load(agent.image) {
                crossfade(true)
            }

            itemView.setOnClickListener {
                onClick(agent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_agent, parent, false)
        return AgentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}