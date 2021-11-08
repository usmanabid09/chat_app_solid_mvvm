package com.example.solidapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        installSplashScreen()
        setTheme(R.style.Theme_SolidApp)
        setContentView(R.layout.activity_main)
    }
}