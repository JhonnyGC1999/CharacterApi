package com.jhonny.example.characterapi.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.jhonny.example.characterapi.data.remote.models.datacharacter
import com.jhonny.example.characterapi.view.viewmodel.CharacterViewModel
import com.jhonny.example.characterapi.view.characterlist.adapter.characteradapter
import com.jhonny.example.characterapi.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Para inyectar dependencias
class CharacterFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: CharacterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.onCreateAllCharacter()
        binding.SVSearch.setOnQueryTextListener(this)
        viewmodel.quotModel.observe(viewLifecycleOwner) {
            initRecyclerView(it.toList())
            if (it.isEmpty()) {
                val query = binding.SVSearch.query.toString()
                Toast.makeText(
                    requireContext(),
                    "lo sentimos, no pudimos encontrar $query que ingreso el usuario, verifica los datos ingresados.".toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initRecyclerView(character: List<datacharacter>) {
        val adapter = characteradapter()
        val layoutManager = GridLayoutManager(requireContext(), 1)
        binding.rvCharacter.adapter = adapter
        binding.rvCharacter.layoutManager = layoutManager
        adapter.setCharacterList(character)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            viewmodel.searchByName(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

}