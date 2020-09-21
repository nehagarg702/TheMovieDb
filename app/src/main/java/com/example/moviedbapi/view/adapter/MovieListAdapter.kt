package com.example.moviesdbapi.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapi.R
import com.example.moviedbapi.databinding.CardRowBinding
import com.example.moviesdbapi.application.MovieDbApiApplication
import com.example.moviesdbapi.model.Movie
import com.example.moviesdbapi.view.callback.MovieClickCallback

/*
** Adapter for adding data to Recycle View. Also used Glide to load the Image from url.
**/
class MovieListAdapter(var callback: MovieClickCallback) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var movies = ArrayList<Movie>()
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : CardRowBinding =DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.card_row, parent, false)
        binding.callback=callback
        return ViewHolder(binding)
    }

    @SuppressLint("RecyclerView")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.movie=movie
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(
                MovieDbApiApplication.mApplicationContext,
                R.anim.item_animation_fall_down
            )
            holder.binding.cardRow.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun getItemCount(): Int {
        return  movies.size
    }

    class ViewHolder(view: CardRowBinding) : RecyclerView.ViewHolder(view.root) {
        val binding=view
    }

    fun setMovieList(movieList: List<Movie>) {
        val size=movies.size
            movies.addAll( movieList)
            notifyItemInserted(size)
    }
}