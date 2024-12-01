package com.jhonny.example.characterapi.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jhonny.example.characterapi.data.remote.models.datacharacter
import com.jhonny.example.characterapi.data.remote.models.datalocation
import com.jhonny.example.characterapi.data.remote.models.dataorigin

@Entity(tableName = "character_table")
data class characterentity(
    @PrimaryKey val id: String,
    val name: String,
    val status: String,
    val species: String? = null,
    val gender: String,
    val origin: String,
    val location : String,
    val image: String,
    )
//toDomain se ocupa para convertir una entidad de room a una respuesta de api
//Esto es mapear

fun characterentity.toDomain() = datacharacter(id.toInt(), name, status, species, gender, dataorigin(origin), datalocation(location), image)
