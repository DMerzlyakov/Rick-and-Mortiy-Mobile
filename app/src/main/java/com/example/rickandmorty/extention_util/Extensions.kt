package com.example.rickandmorty.extention_util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.rickandmorty.R


/** Функция-расширение для загрузки аватара по URL */
fun ImageView.setImageFromUrl(url: String, context: Context) {
    Glide.with(context)
        .load(url)
        .transform(RoundedCorners(20))
        .placeholder(R.drawable.gray_gradient)
        .into(this)
}