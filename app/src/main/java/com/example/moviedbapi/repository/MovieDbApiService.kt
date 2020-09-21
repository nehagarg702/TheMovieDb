package com.example.moviesdbapi.repository

import com.example.moviesdbapi.model.Movie
import com.example.moviesdbapi.model.MovieDbResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbApiService {

    @GET("upcoming/movie")
    fun getMovieResult(@Query("api_key") apiKey: String, @Query ("page") page : Int) : Call<MovieDbResult>

    @GET("discover/movie")
    fun getSearchResults(@Query("sort_by") sortBy: String, @Query("api_key") apiKey: String,@Query ("page") page : Int): Call<MovieDbResult>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId:Long,@Query("api_key") apiKey: String) : Call<Movie>
}