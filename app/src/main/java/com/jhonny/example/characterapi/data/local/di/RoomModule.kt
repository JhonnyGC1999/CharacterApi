package com.jhonny.example.characterapi.data.local.di

import android.content.Context
import androidx.room.Room
import com.jhonny.example.characterapi.data.local.database.characterdatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): characterdatabase {
        return Room.databaseBuilder(context, characterdatabase::class.java, "character_database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(db: characterdatabase) = db.characterdao()
}