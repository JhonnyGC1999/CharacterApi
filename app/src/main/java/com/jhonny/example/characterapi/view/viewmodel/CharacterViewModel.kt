package com.jhonny.example.characterapi.view.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhonny.example.characterapi.data.local.database.characterdatabase
import com.jhonny.example.characterapi.data.remote.models.datacharacter
import com.jhonny.example.characterapi.data.remote.service.apiconsume
import com.jhonny.example.characterapi.data.repository.characterrepository
import kotlinx.coroutines.launch


class CharacterViewModel(): ViewModel() {

    lateinit var context : Context

    private val repository : characterrepository by lazy {
        characterrepository(apiconsume(), characterdatabase.getDatabase(context).characterdao(), context)
    }

    val quotModel = MutableLiveData<Array<datacharacter>>()

    fun setContextViewModel(context: Context) {
        this.context = context
    }

    fun onCreateAllCharacter() {
        viewModelScope.launch {
            val result = repository.getDataCharacter()
            if (result.isNotEmpty()) {
                quotModel.postValue(result)
            }
            else{
                quotModel.postValue(arrayOf(datacharacter.empty()))
            }
        }
    }

    fun searchByName(name: String) {
        viewModelScope.launch {
            val result = repository.searchByName(name)
            if (result.isNotEmpty()) {
                quotModel.postValue(result)
            }
            else{
                quotModel.postValue(arrayOf(datacharacter.empty()))
            }
        }
    }

    fun searchById(id: Int) {
        viewModelScope.launch{
            val result = repository.getCharacterById(id)
            quotModel.postValue(arrayOf(result))
        }

    }
}