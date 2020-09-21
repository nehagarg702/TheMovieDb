package com.example.moviesdbapi.view.callback

import com.example.moviesdbapi.model.Movie

interface MovieClickCallback {
    fun onClick(movieid : Long)
}