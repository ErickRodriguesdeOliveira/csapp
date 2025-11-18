package com.example.csapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.csapp.data.Crate

class CrateAdapter(
    private var items: List<Crate>,
    private val onClick: (Crate) -> Unit
) : RecyclerView.Adapter<CrateAdapter.CrateViewHolder>() {

    fun updateData(newItems: List<Crate>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class CrateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgCrate: ImageView = itemView.findViewById(R.id.imgCrate)
        private val tvName: TextView = itemView.findViewById(R.id.tvCrateName)
        private val tvInfo: TextView = itemView.findViewById(R.id.tvCrateInfo)

        fun bind(crate: Crate) {
            tvName.text = crate.name

            val type = crate.type ?: "Tipo desconhecido"
            val date = crate.first_sale_date ?: "Sem data"
            tvInfo.text = "$type • $date"

            imgCrate.load(crate.image) {
                crossfade(true)
            }

            itemView.setOnClickListener {
                onClick(crate)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_crate, parent, false)
        return CrateViewHolder(view)
    }

    override fun onBindViewHolder(holder: CrateViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
