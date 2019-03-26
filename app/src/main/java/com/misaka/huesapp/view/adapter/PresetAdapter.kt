package com.misaka.huesapp.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.misaka.huesapp.R
import com.misaka.huesapp.model.Preset


class PresetAdapter(private val presetList: ArrayList<Preset>, val itemClick: (Int) -> Unit): RecyclerView.Adapter<PresetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return presetList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindItems(presetList[position])
        viewHolder.itemView.setOnClickListener {itemClick(position)}
    }

     class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
        fun bindItems(preset: Preset) {
            val textView = itemView.findViewById<TextView>(R.id.presetName)
            val imageView = itemView.findViewById<ImageView>(R.id.presetImageView)
            textView.text = preset.name
            Glide.with(itemView.context).load(preset.image).into(imageView)
        }
    }
}