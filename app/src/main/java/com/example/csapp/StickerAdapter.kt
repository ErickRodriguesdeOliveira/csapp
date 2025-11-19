package com.example.csapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.csapp.data.Sticker

class StickerAdapter(
    private var items: List<Sticker>,
    private val onClick: (Sticker) -> Unit
) : RecyclerView.Adapter<StickerAdapter.StickerViewHolder>() {

    fun updateData(newItems: List<Sticker>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class StickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgSticker: ImageView = itemView.findViewById(R.id.imgSticker)
        private val tvName: TextView = itemView.findViewById(R.id.tvStickerName)
        private val tvRarity: TextView = itemView.findViewById(R.id.tvStickerRarity)
        private val tvExtra: TextView = itemView.findViewById(R.id.tvStickerExtra)

        fun bind(sticker: Sticker) {
            tvName.text = sticker.name
            val rarityName = sticker.rarity?.name ?: "Raridade desconhecida"
            tvRarity.text = rarityName
            tvExtra.text = "Toque para ver detalhes"

            imgSticker.load(sticker.image) {
                crossfade(true)
            }

            itemView.setOnClickListener {
                onClick(sticker)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StickerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sticker, parent, false)
        return StickerViewHolder(view)
    }

    override fun onBindViewHolder(holder: StickerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
