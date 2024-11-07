package com.jhonny.example.characterapi.View.characterlist.adapter

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jhonny.example.characterapi.Data.remote.models.datacharacter
import com.jhonny.example.characterapi.R
import com.jhonny.example.characterapi.R.id.action_characterFragment_to_characterViewFragment
import com.jhonny.example.characterapi.databinding.ItemCharacterBinding

class characterviewholder (val binding : ItemCharacterBinding): RecyclerView.ViewHolder(binding.root) {

    fun render(character: datacharacter) {
        Glide
            .with(binding.root.context)
            .load(character.image)
            .centerCrop()
            .into(binding.imgFoto)

        val id = character.id
        binding.tvNombre.text = character.name
        binding.cbLiveDie.isChecked = character.status == "Alive"
        binding.tvEstado.text = character.status
        binding.tvUbicacion.text = character.location.name
        binding.tvEspecie.text = character.species
        binding.tvOrigen.text = character.location.name



        binding.clPersonajes.setOnClickListener {
            val bundle = bundleOf("characterid" to id)
            binding.root.findNavController().navigate(
                action_characterFragment_to_characterViewFragment, bundle)
        }
    }

}