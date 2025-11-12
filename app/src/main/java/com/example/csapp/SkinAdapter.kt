package com.example.csapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.csapp.data.Skin

class SkinAdapter(
    private var items: List<Skin>,
    private val onClick: (Skin) -> Unit
) : RecyclerView.Adapter<SkinAdapter.SkinViewHolder>() {

    fun updateData(newItems: List<Skin>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class SkinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgSkin: ImageView = itemView.findViewById(R.id.imgSkin)
        private val tvName: TextView = itemView.findViewById(R.id.tvSkinName)
        private val tvWeapon: TextView = itemView.findViewById(R.id.tvSkinWeapon)
        private val tvExtra: TextView = itemView.findViewById(R.id.tvSkinExtra)

        fun bind(skin: Skin) {
            tvName.text = skin.name
            val weaponName = skin.weapon?.name ?: "Arma desconhecida"
            val rarityName = skin.rarity?.name ?: "Raridade desconhecida"
            tvWeapon.text = "$weaponName • $rarityName"
            tvExtra.text = "Toque para ver detalhes"

            imgSkin.load(skin.image) {
                crossfade(true)
            }

            itemView.setOnClickListener {
                onClick(skin)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkinViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_skin, parent, false)
        return SkinViewHolder(view)
    }

    override fun onBindViewHolder(holder: SkinViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
