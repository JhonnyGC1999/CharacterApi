package com.jhonny.example.characterapi.View.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhonny.example.characterapi.Data.remote.models.datacharacter
import com.jhonny.example.characterapi.Data.remote.service.apiconsume
import com.jhonny.example.characterapi.Data.repository.characterrepository
import kotlinx.coroutines.launch

class characterViewModel: ViewModel() {

    private val repository = characterrepository(apiconsume())
    val quotModel = MutableLiveData<Array<datacharacter>>()

    fun onCreateAllCharacter() {
        viewModelScope.launch {
            val result = repository.obtenerDatos()
            if (!result.isNullOrEmpty()) {
                quotModel.postValue(result)
            }
        }
    }

    fun searchByName(name: String) {
        viewModelScope.launch {
            val result = repository.searchByName(name)
            if (!result.isNullOrEmpty()) {
                quotModel.postValue(result)
            }
        }
    }

    fun searchById(id: Int) {
        viewModelScope.launch{
            val result = repository.getCharacterById(id)
            quotModel.postValue(arrayOf(result!!))
        }
    }
}