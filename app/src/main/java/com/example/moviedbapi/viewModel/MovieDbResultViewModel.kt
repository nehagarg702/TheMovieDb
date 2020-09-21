package com.example.moviesdbapi.viewModel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.moviesdbapi.model.MovieDbResult
import com.example.moviesdbapi.repository.MovieRepository
import javax.inject.Inject

//Going to observe the MovieDbResult liveData
class MovieDbResultViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var movieDbResultObserver : MutableLiveData<MovieDbResult>
    private var page : ObservableField<Int> = ObservableField()
    private lateinit var repository: MovieRepository


    constructor(repository: MovieRepository, application: Application,page: Int) : this(application) {
        this.page.set(page)
        this.repository=repository
        movieDbResultObserver=repository.getMovieDbResult(this.page.get()!!)
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getMovieDbResultObserver():LiveData<MovieDbResult>{
        return movieDbResultObserver
    }

    fun setPageNo(page: Int )
    {
        this.page.set(page)
        movieDbResultObserver=repository.getMovieDbResult(this.page.get()!!)
    }

}