package com.jhonny.example.characterapi.data.remote.service

import com.jhonny.example.characterapi.data.remote.models.datacharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class apiconsume @Inject constructor(private val api: apiservice) {

    suspend fun getAllCharacter(): Array<datacharacter> {
        return withContext(Dispatchers.IO) {
            val response = api.getDataCharacters()
            if (response.isSuccessful) {
                val allcharacter = response.body()
                return@withContext allcharacter?.results ?: emptyArray<datacharacter>()
            } else {
                return@withContext emptyArray<datacharacter>()
            }
        }
    }

    suspend fun getCharacterByName(name: String): Array<datacharacter> {
        return withContext(Dispatchers.IO) {
            val response = api.getDataCharacterName(name)
            if (response.isSuccessful) {
                val nameCharacter = response.body()
                return@withContext nameCharacter?.results ?: emptyArray<datacharacter>()
            } else {
                return@withContext emptyArray<datacharacter>()
            }
        }
    }

    suspend fun getCharacterById(id: Int): datacharacter? {
        return withContext(Dispatchers.IO) {
            val response = api.getDataCharacterId(id)
            if (response.isSuccessful) {
                val idCharacter = response.body()
                return@withContext idCharacter
            } else {
                return@withContext null
            }
        }
    }
}
