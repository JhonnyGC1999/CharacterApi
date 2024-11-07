package com.jhonny.example.characterapi.Data.remote.models

data class datacharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String? = null,
    val gender: String,
    val origin: dataorigin,
    val location : datalocation,
    val image: String,
)
