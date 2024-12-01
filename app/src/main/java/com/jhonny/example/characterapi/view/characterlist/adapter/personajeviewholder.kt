package com.jhonny.example.characterapi.view.characterlist.adapter

import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jhonny.example.characterapi.view.characterlist.data.datpersonajes
import com.jhonny.example.characterapi.databinding.ItemCharacterBinding

class personajeviewholder (val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root) {
    fun render(personajes: datpersonajes){

        Glide
            .with(binding.root.context)
            .load(personajes.characterImage)
            .centerCrop()
            .into(binding.imgFoto)


        binding.tvNombre.text = personajes.fullName
        binding.cbLiveDie.isChecked = personajes.isLive
        binding.tvEstado.text = personajes.liveDie
        binding.tvUbicacion.text = personajes.lasKnownLocation
        binding.tvEspecie.text = personajes.specie
        binding.tvOrigen.text = personajes.origin


        binding.clPersonajes.setOnClickListener {
            if (personajes.accion != 0)
                binding.root.findNavController().navigate(personajes.accion)
            else
                Toast.makeText(binding.root.context, "Proximamente :(", Toast.LENGTH_SHORT).show()
        }
    }
}