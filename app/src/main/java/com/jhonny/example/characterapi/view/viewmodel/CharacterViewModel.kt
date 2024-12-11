package com.jhonny.example.characterapi.view.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhonny.example.characterapi.data.remote.models.datacharacter
import com.jhonny.example.characterapi.data.remote.service.apiconsume
import com.jhonny.example.characterapi.data.repository.characterrepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(val repository: characterrepository) : ViewModel() {

    val quotModel = MutableLiveData<Array<datacharacter>>()

    fun onCreateAllCharacter() {
        viewModelScope.launch {
            val result = repository.getDataCharacter()
            quotModel.postValue(result)
        }
    }

    fun searchByName(name: String) {
        viewModelScope.launch {
            val result = repository.searchByName(name)
            quotModel.postValue(result)
        }
    }

    fun searchById(id: Int) {
        viewModelScope.launch {
            val result = repository.getCharacterById(id)
            quotModel.postValue(arrayOf(result))
        }
    }
}