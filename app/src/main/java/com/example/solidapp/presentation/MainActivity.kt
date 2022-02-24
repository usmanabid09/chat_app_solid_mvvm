package com.example.solidapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.solidapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        installSplashScreen()
        setTheme(R.style.Theme_SolidApp)
        setContentView(R.layout.activity_main)
    }
}