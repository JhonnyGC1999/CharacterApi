package com.jhonny.example.characterapi.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.jhonny.example.characterapi.Data.remote.models.datacharacter
import com.jhonny.example.characterapi.View.ViewModel.characterViewModel
import com.jhonny.example.characterapi.View.characterlist.adapter.characteradapter
import com.jhonny.example.characterapi.databinding.FragmentCharacterBinding



class CharacterFragment : Fragment(), SearchView.OnQueryTextListener{

    private var _binding : FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    private val viewmodel : characterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //obtenerDatos()
        viewmodel.onCreateAllCharacter()
        binding.SVSearch.setOnQueryTextListener(this)
        viewmodel.quotModel.observe(viewLifecycleOwner) {
             initRecyclerView(it.toList())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

/*    private fun searchByName(name: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getapi.getRetroFit().create(apiservice::class.java).getDataCharacterName(name)
                val allcharacter = call.body()
                if (call.isSuccessful) {
                    if (allcharacter != null) {
                        withContext(Dispatchers.Main) {
                            initRecyclerView(allcharacter.results.toList())
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(),"Error: ${call.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error de conexión ${e.message}" , Toast.LENGTH_SHORT).show()
                    Log.e("error", e.message.toString())
                }
            }
        }
    }*/

/*    private fun obtenerDatos(){
        binding.load.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getapi.getRetroFit() .create(apiservice::class.java).getDataCharacters()
                val allcharacter = call.body()
                if (call.isSuccessful) {
                    if (allcharacter != null) {
                        withContext(Dispatchers.Main) {
                            initRecyclerView(allcharacter.results.toList())
                            binding.load.isVisible = false
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(),"Error: ${call.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }*/

    private fun initRecyclerView(character:List<datacharacter>){
        val adapter = characteradapter()
        val layoutManager = GridLayoutManager(requireContext(),1)
        binding.rvCharacter.adapter = adapter
        binding.rvCharacter.layoutManager = layoutManager
        adapter.setCharacterList(character)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            //searchByName(query)
            viewmodel.searchByName(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
       return true
    }
}