package com.jhonny.example.characterapi.view.characterlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.jhonny.example.characterapi.view.characterlist.adapter.personajeadapter
import com.jhonny.example.characterapi.view.characterlist.data.personajeprovider
import com.jhonny.example.characterapi.databinding.FragmentActivityBinding


class ActivityFragment : Fragment() {

    private var _binding : FragmentActivityBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setUpRecycler(){
        val adapter = personajeadapter()
        val layoutManager = GridLayoutManager(requireContext(),1)
        binding.rvActivitys.adapter = adapter
        binding.rvActivitys.layoutManager = layoutManager
        adapter.setMenu(personajeprovider.datos)
    }
}