package com.example.moviedbapi.view.ui

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import com.example.moviedbapi.R
import com.example.moviedbapi.databinding.ActivityMovieDetailBinding
import com.example.moviesdbapi.application.MovieDbApiApplication
import com.example.moviesdbapi.dependencyInjection.component.DaggerMovieDbComponent
import com.example.moviesdbapi.dependencyInjection.module.ContextModule
import com.example.moviesdbapi.model.Movie
import com.example.moviesdbapi.repository.MovieRepository
import com.example.moviesdbapi.utilities.Constant
import com.example.moviesdbapi.viewModel.MovieResultViewModel

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMovieDetailBinding=  DataBindingUtil.setContentView(this,R.layout.activity_movie_detail)
        binding.isLoadingData=true
        setSupportActionBar(findViewById(R.id.toolbar_movie))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        val movieId =intent.getLongExtra(Constant.MOVIE_ID,0)
        val service= DaggerMovieDbComponent.builder().contextModule(ContextModule(applicationContext)).build()
        val viewModel=MovieResultViewModel(MovieDbApiApplication(), MovieRepository(service.getMovieDbService()),movieId)
        viewModel.getObservableProject().observe(this,{
            binding.movie=it
            binding.isLoadingData=false
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}