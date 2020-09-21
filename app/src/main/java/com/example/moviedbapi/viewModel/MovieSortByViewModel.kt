package com.example.moviesdbapi.viewModel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.moviesdbapi.model.Movie
import com.example.moviesdbapi.model.MovieDbResult
import com.example.moviesdbapi.repository.MovieRepository

class MovieSortByViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var movieResultObserver : LiveData<MovieDbResult>
    private var sortBy :ObservableField<String> = ObservableField()
    var movie: ObservableField<Movie> = ObservableField()
    private lateinit var repository: MovieRepository


    constructor(application: Application, repository: MovieRepository, sortBy: String,page:Int) : this(application) {
        this.sortBy.set(sortBy)
        this.repository=repository
        movieResultObserver=repository.getMovieDbSortByResult(sortBy,page)
    }

    fun getObservableProject(): LiveData<MovieDbResult> {
        return movieResultObserver
    }

    fun setProject(movie: Movie) {
        this.movie.set(movie)
    }

    fun setSortBy(sortBy : String,page: Int)
    {
        this.sortBy.set(sortBy)
        movieResultObserver=repository.getMovieDbSortByResult(sortBy,page)
    }

}