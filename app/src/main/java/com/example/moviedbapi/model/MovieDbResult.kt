package com.example.moviesdbapi.model

data class MovieDbResult(val page: Long,val total_results : Long, val total_pages : Long, val results : List<Movie>)