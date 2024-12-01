package com.jhonny.example.characterapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.jhonny.example.characterapi.data.remote.models.datacharacter
import com.jhonny.example.characterapi.view.viewmodel.CharacterViewModel
import com.jhonny.example.characterapi.databinding.FragmentCharacterViewBinding


class CharacterViewFragment : Fragment() {

    private var _binding : FragmentCharacterViewBinding? = null
    private val binding get() = _binding!!
    private val viewmodel : CharacterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.setContextViewModel(requireContext())
            val characterid = arguments?.getInt("characterid")
            if (characterid != null) {
                viewmodel.searchById(characterid)
                viewmodel.quotModel.observe(viewLifecycleOwner) {
                    render(viewmodel.quotModel.value!![0])
                }
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterViewBinding.inflate(inflater, container, false)
        return binding.root

    }

    fun render(character: datacharacter) {
        Glide
            .with(binding.root.context)
            .load(character.image )
            .into(binding.imgPicture)
        binding.tvName.text = character.name
        binding.cbLivedie.isChecked = character.status == "Alive"
        binding.tvState.text = character.status
        binding.tvUbication.text = character.location.name
        binding.tvRace.text = character.species
        binding.tvOrigin.text = character.location.name
    }
}