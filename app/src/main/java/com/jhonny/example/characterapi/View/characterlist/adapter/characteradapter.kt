package com.jhonny.example.characterapi.View.characterlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhonny.example.characterapi.Data.remote.models.datacharacter
import com.jhonny.example.characterapi.R
import com.jhonny.example.characterapi.databinding.ItemCharacterBinding

class characteradapter : RecyclerView.Adapter<characterviewholder>() {

    private var characterList:List<datacharacter> = emptyList()


    fun setCharacterList(character:List<datacharacter>){
        characterList = character
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): characterviewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_character, parent, false)
        val binding = ItemCharacterBinding.bind(view)
        return characterviewholder(binding)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: characterviewholder, position: Int) {
        val character = characterList[position]
        holder.render(character)
    }
}