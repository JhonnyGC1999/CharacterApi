package com.jhonny.example.characterapi.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.jhonny.example.characterapi.Data.remote.models.datacharacter
import com.jhonny.example.characterapi.Data.remote.service.apiservice
import com.jhonny.example.characterapi.Data.remote.service.getapi
import com.jhonny.example.characterapi.R
import com.jhonny.example.characterapi.databinding.FragmentCharacterViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CharacterViewFragment : Fragment() {

    private var _binding : FragmentCharacterViewBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val characterid = arguments?.getInt("characterid")

        if (characterid != null) {
            searchById(characterid)
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterViewBinding.inflate(inflater, container, false)
        return binding.root

    }

    private fun searchById(id: Int) {
        binding.load.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getapi.getRetroFit().create(apiservice::class.java).getDataCharacterId(id)
                val allcharacter = call.body()
                if (call.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        if (allcharacter != null) {
                            render(allcharacter)
                            binding.load.isVisible = false
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Error: ${call.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun render(character: datacharacter) {
        Glide
            .with(binding.root.context)
            .load(character.image)
            .into(binding.imgPicture)

        binding.tvName.text = character.name
        binding.cbLivedie.isChecked = character.status == "Alive"
        binding.tvState.text = character.status
        binding.tvUbication.text = character.location.name
        binding.tvRace.text = character.species
        binding.tvOrigin.text = character.location.name

    }
}