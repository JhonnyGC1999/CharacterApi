package com.jhonny.example.characterapi.Data.repository


import com.jhonny.example.characterapi.Data.remote.models.datacharacter
import com.jhonny.example.characterapi.Data.remote.service.apiconsume


class characterrepository(
    private val api : apiconsume
){
    suspend fun obtenerDatos() : Array<datacharacter> {
        return api.getAllCharacter()
    }

    suspend fun searchByName(name: String) : Array<datacharacter> {
        return api.getCharacterByName(name)
    }

    suspend fun getCharacterById(id : Int) : datacharacter? {
        return api.getCharacterById(id)
    }
}




