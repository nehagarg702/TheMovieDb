package com.example.moviesdbapi.view.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @BindingAdapter("image")
    fun loadImage(view: ImageView, imageUrl: String?) {
        if(!imageUrl.isNullOrEmpty()) {
            Glide.with(view.context)
                .load("https://image.tmdb.org/t/p/original/$imageUrl")
                .into(view)
        }
    }