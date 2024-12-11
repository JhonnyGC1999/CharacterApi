package com.jhonny.example.characterapi.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jhonny.example.characterapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint //Indica que esta clase se puede inyectar
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
    }

    companion object {
        lateinit var context: Context
    }
}