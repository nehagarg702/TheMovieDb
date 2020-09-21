package com.example.moviesdbapi.viewModel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.moviesdbapi.model.Movie
import com.example.moviesdbapi.repository.MovieRepository

class MovieResultViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var movieResultObserver : LiveData<Movie>
    private var movieId :Long =0
    var movie: ObservableField<Movie> = ObservableField()

    constructor(application: Application, repository: MovieRepository, movieId: Long) : this(application) {
        this.movieId=movieId
        movieResultObserver=repository.getMovieDetail(movieId)
    }

    fun getObservableProject(): LiveData<Movie> {
        return movieResultObserver
    }

    fun setProject(movie: Movie) {
        this.movie.set(movie)
    }

    fun setMovieTd(id : Long)
    {
        this.movieId=id
    }

}