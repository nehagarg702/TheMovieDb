package com.example.moviesdbapi.model

import java.io.Serializable

data class Movie(val popularity : Float,val vote_count : Long, val video : Boolean,val poster_path : String, val id : Long,val original_title : String,
                 val title : String, val vote_average : Float, val overview : String, val release_date : String)