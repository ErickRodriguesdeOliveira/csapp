package com.example.csapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.csapp.data.Highlight

class HighlightAdapter(
    private var items: List<Highlight>,
    private val onClick: (Highlight) -> Unit
) : RecyclerView.Adapter<HighlightAdapter.HighlightViewHolder>() {

    fun updateData(newItems: List<Highlight>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class HighlightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgHighlight: ImageView = itemView.findViewById(R.id.imgHighlight)
        private val tvName: TextView = itemView.findViewById(R.id.tvHighlightName)
        private val tvInfo: TextView = itemView.findViewById(R.id.tvHighlightInfo)

        fun bind(highlight: Highlight) {
            tvName.text = highlight.name

            val event = highlight.tournament_event ?: "Evento desconhecido"
            val teams = "${highlight.team0 ?: "??"} vs ${highlight.team1 ?: "??"}"
            tvInfo.text = "$event • $teams"

            imgHighlight.load(highlight.image) {
                crossfade(true)
            }

            itemView.setOnClickListener {
                onClick(highlight)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_highlight, parent, false)
        return HighlightViewHolder(view)
    }

    override fun onBindViewHolder(holder: HighlightViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
