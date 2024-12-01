package com.jhonny.example.characterapi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jhonny.example.characterapi.data.local.entity.characterentity


@Dao
interface characterdao {

    @Query("SELECT * FROM character_table")
    fun getAllCharacters(): List<characterentity>

    @Query("SELECT * FROM character_table WHERE id = :id limit 1")
    fun getCharacterById(id: Int): characterentity?

    @Query("SELECT * FROM character_table WHERE name LIKE '%' || :name || '%'")
    fun getCharacterByName(name: String): List<characterentity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(character: List<characterentity>)
}