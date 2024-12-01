package com.jhonny.example.characterapi.data.repository


import android.content.Context
import android.util.Log
import com.jhonny.example.characterapi.data.local.dao.characterdao
import com.jhonny.example.characterapi.data.local.entity.toDomain
import com.jhonny.example.characterapi.data.remote.models.datacharacter
import com.jhonny.example.characterapi.data.remote.models.toRoom
import com.jhonny.example.characterapi.data.remote.service.apiconsume
import com.jhonny.example.characterapi.view.hasInternetConnection


class characterrepository(
    private val api : apiconsume, private val db : characterdao,
private val context : Context)
{

    suspend fun getDataCharacter() : Array<datacharacter> {
        return if (hasInternetConnection(context)) {
            Log.d("Jhonny", "Entra a internet")
            val character = api.getAllCharacter()
            insertCharacter(character.toList())
            character
        }
        else {
            Log.d("Jhonny", "No entra internet")
            db.getAllCharacters().map { characterentity -> characterentity.toDomain()}.toTypedArray()
        }
    }

    suspend fun searchByName(name: String) : Array<datacharacter> {
        return if (hasInternetConnection(context)){
            val character = api.getCharacterByName(name)
            insertCharacter(character.toList())
            character
        } else {
            //.map mapeo de los datos para convertir una entidad de room a una respuesta de api
            db.getCharacterByName(name).map { characterentity -> characterentity.toDomain()}.toTypedArray()
        }
    }

    suspend fun getCharacterById(id : Int) : datacharacter {
        return if (hasInternetConnection(context)){
            //:? elvis operator
            api.getCharacterById(id)?: datacharacter.empty()
        } else {
            db.getCharacterById(id)?.toDomain() ?: datacharacter.empty()
        }
    }

    fun insertCharacter(character: List<datacharacter>) {
        db.insertAll(character.map { datacharacter -> datacharacter.toRoom() })
    }
}




