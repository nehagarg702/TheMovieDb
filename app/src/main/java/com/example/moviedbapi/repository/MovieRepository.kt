package com.example.moviesdbapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesdbapi.model.Movie
import com.example.moviesdbapi.model.MovieDbResult
import com.example.moviesdbapi.utilities.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository(private var movieDbApiService: MovieDbApiService) {

    fun getMovieDbResult(page : Int): MutableLiveData<MovieDbResult> {
        val movieDbResult=MutableLiveData<MovieDbResult>()
        movieDbApiService.getMovieResult(Constant.API_KEY,page).enqueue(object : Callback<MovieDbResult> {
                override fun onResponse(call: Call<MovieDbResult>, response: Response<MovieDbResult>) {
                    if(response.body()!=null && !response.body()!!.results.isNullOrEmpty())
                    movieDbResult.value=response.body()
                }

                override fun onFailure(call: Call<MovieDbResult>, t: Throwable) {
                    movieDbResult.value=null
                }
            })
        return movieDbResult
    }

    fun getMovieDbSortByResult(sortBy:String,page:Int): LiveData<MovieDbResult>{
        val movieDbResult=MutableLiveData<MovieDbResult>()
        movieDbApiService.getSearchResults(sortBy,Constant.API_KEY,page).enqueue(object : Callback<MovieDbResult> {
            override fun onResponse(call: Call<MovieDbResult>, response: Response<MovieDbResult>) {
                if(response.body()!=null && !response.body()!!.results.isNullOrEmpty())
                    movieDbResult.value=response.body()
            }

            override fun onFailure(call: Call<MovieDbResult>, t: Throwable) {
                movieDbResult.value=null
            }
        })
        return movieDbResult
    }

    fun getMovieDetail(movieId:Long): LiveData<Movie>{
        val movieDbResult=MutableLiveData<Movie>()
        movieDbApiService.getMovieDetails(movieId,Constant.API_KEY).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if(response.body()!=null)
                    movieDbResult.value=response.body()
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                movieDbResult.value=null
            }
        })
        return movieDbResult
    }

}