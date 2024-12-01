package com.jhonny.example.characterapi.data.remote.models

import com.jhonny.example.characterapi.data.local.entity.characterentity

data class datacharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String? = null,
    val gender: String,
    val origin: dataorigin,
    val location : datalocation,
    val image: String,
){
    //Evita que se rompa el codigo en caso de que no haya nullo.
    companion object {
        fun empty() = datacharacter(0,"","","","", dataorigin(""), datalocation(""),"")
    }
}

fun datacharacter.toRoom() = characterentity(id.toString(), name, status, species, gender, origin.name, location.name, image)

