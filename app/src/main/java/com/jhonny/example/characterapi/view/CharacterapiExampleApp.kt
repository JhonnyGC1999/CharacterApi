package com.jhonny.example.characterapi.view

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp //Indica que esta clase es un punto de entrada de Hilt
class CharacterapiExampleApp :
    Application() //Hereda de Application para que pueda ser utilizada por Hilt