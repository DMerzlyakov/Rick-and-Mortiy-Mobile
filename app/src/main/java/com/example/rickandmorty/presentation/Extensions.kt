package com.example.rickandmorty.presentation

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R


/** Функция-расширение для загрузки аватара по URL */
fun ImageView.setImageFromUrl(url: String, context: Context) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.gray_gradient)
        .into(this)
}