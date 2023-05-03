package com.example.rickandmorty.extention_util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable


/** Функция-расширение для загрузки аватара по URL */
fun ImageView.setImageFromUrl(url: String, context: Context) {
    Glide.with(context)
        .load(url)
        .transform(RoundedCorners(20))
        .placeholder(ShimmerDrawable().apply {
            setShimmer(
                Shimmer.AlphaHighlightBuilder().setBaseAlpha(0.7f)
                    .setHighlightAlpha(0.6f).build()
            )
        })
        .into(this)
}