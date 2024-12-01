package com.jhonny.example.characterapi.data.remote.service

import com.jhonny.example.characterapi.data.remote.models.datacharacter
import com.jhonny.example.characterapi.data.remote.models.dataresultapi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface apiservice {

   @GET("character")
    suspend fun getDataCharacters(): Response<dataresultapi>

    @GET("character/{id}")
    suspend fun getDataCharacterId(@Path("id")id : Int): Response<datacharacter>

    @GET("character/")
    suspend fun getDataCharacterName(@Query("name") name: String): Response<dataresultapi>

 /*@GET("character/") Para filtrar mas datos
 suspend fun getDataCharacterName(@Query("name") name: String, @Query("status") status : String): Response<resultapi>*/
}