package com.jhonny.example.characterapi.data.repository


import android.content.Context
import android.util.Log
import com.jhonny.example.characterapi.data.local.dao.characterdao
import com.jhonny.example.characterapi.data.local.entity.toDomain
import com.jhonny.example.characterapi.data.remote.models.datacharacter
import com.jhonny.example.characterapi.data.remote.models.toRoom
import com.jhonny.example.characterapi.data.remote.service.apiconsume
import com.jhonny.example.characterapi.view.hasInternetConnection
import javax.inject.Inject


class characterrepository @Inject constructor(
    private val api: apiconsume,
    private val db: characterdao
) {

    suspend fun getDataCharacter(): Array<datacharacter> {
        return if (hasInternetConnection()) {
            Log.d("Jhonny", "Entra a internet")
            val character = api.getAllCharacter()
            insertCharacter(character.toList())
            character
        } else {
            Log.d("Jhonny", "No entra internet")
            db.getAllCharacters().map { characterentity -> characterentity.toDomain() }
                .toTypedArray()
        }
    }

    suspend fun searchByName(name: String): Array<datacharacter> {
        return if (hasInternetConnection()) {
            val character = api.getCharacterByName(name)
            insertCharacter(character.toList())
            character
        } else {
            db.getCharacterByName(name).map { characterentity -> characterentity.toDomain() }
                .toTypedArray()
        }
    }

    suspend fun getCharacterById(id: Int): datacharacter {
        return if (hasInternetConnection()) {
            api.getCharacterById(id) ?: datacharacter.empty()
        } else {
            db.getCharacterById(id)?.toDomain() ?: datacharacter.empty()
        }
    }

    fun insertCharacter(character: List<datacharacter>) {
        db.insertAll(character.map { datacharacter -> datacharacter.toRoom() })
    }
}




