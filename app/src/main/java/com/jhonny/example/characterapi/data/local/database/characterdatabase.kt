package com.jhonny.example.characterapi.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jhonny.example.characterapi.data.local.dao.characterdao
import com.jhonny.example.characterapi.data.local.entity.characterentity


@Database(entities = [characterentity::class], version = 1, exportSchema = false)
abstract class characterdatabase : RoomDatabase() {
    companion object {
        private var database: characterdatabase? = null
        private const val DATABASE_NAME = "character_database"
        fun getDatabase(context: Context): characterdatabase {
            if (database == null) {
                database =
                    Room.databaseBuilder(context, characterdatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            }
            return database!!
        }
    }

    abstract fun characterdao(): characterdao
}
