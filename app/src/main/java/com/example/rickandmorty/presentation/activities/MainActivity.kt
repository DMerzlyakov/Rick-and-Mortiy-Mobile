package com.example.rickandmorty.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rickandmorty.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        setContentView(R.layout.activity_main)
    }
}