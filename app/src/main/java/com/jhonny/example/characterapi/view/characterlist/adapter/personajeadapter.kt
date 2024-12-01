package com.jhonny.example.characterapi.view.characterlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhonny.example.characterapi.R
import com.jhonny.example.characterapi.view.characterlist.data.datpersonajes
import com.jhonny.example.characterapi.databinding.ItemCharacterBinding

class personajeadapter : RecyclerView.Adapter<personajeviewholder>() {

    private var menuList:List<datpersonajes> = emptyList()

    fun setMenu(menu:List<datpersonajes>){
        menuList = menu
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): personajeviewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate((R.layout.item_character), parent,false)
        val binding = ItemCharacterBinding.bind(view)
        return personajeviewholder(binding)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: personajeviewholder, position: Int) {
        val menu = menuList[position]
        holder.render(menu)
    }
}