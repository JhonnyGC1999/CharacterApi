package com.jhonny.example.characterapi.Data.remote.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object getapi {
    fun getRetroFit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}