package com.jhonny.example.characterapi.data.remote.di

import com.jhonny.example.characterapi.data.remote.service.apiservice
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module //Permite a dagger proveer dependencias
@InstallIn(SingletonComponent::class) //Alcance de la clase
object NetworkModule {

    @Singleton //Solo se crea una instancia
    @Provides//Proporciona una instancia
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): apiservice {
        return retrofit.create(apiservice::class.java)
    }
}
