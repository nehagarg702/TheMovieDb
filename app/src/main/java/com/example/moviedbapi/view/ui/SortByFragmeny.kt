package com.example.moviesdbapi.view.ui

import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviedbapi.R
import com.example.moviedbapi.databinding.FragmentFirstBinding
import com.example.moviedbapi.view.ui.MovieDetailActivity
import com.example.moviesdbapi.application.MovieDbApiApplication
import com.example.moviesdbapi.dependencyInjection.component.DaggerMovieDbComponent
import com.example.moviesdbapi.dependencyInjection.module.ContextModule
import com.example.moviesdbapi.repository.MovieRepository
import com.example.moviesdbapi.utilities.Constant
import com.example.moviesdbapi.utilities.NetworkUtil
import com.example.moviesdbapi.view.adapter.MovieListAdapter
import com.example.moviesdbapi.view.callback.MovieClickCallback
import com.example.moviesdbapi.viewModel.MovieDbResultViewModel
import com.example.moviesdbapi.viewModel.MovieSortByViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SortByFragmeny : Fragment(),MovieClickCallback, SwipeRefreshLayout.OnRefreshListener {

    lateinit var binding: FragmentFirstBinding
    lateinit var movieListAdapter : MovieListAdapter
    lateinit var movieViewModel : MovieSortByViewModel
    var page=1
    var loading=false
    var sortBy : String =""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        sortBy= requireArguments().getString("sortBy").toString()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)
        binding.isLoadingData = true
        binding.openDrawer = true
        binding.textViewText = "Loading Data"
        movieListAdapter = MovieListAdapter(this)
        binding.cardRecyclerView.adapter = movieListAdapter
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        binding.cardRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!binding.cardRecyclerView.canScrollVertically(0) && !loading) {
                    Log.e("click", "click $page")
                    movieViewModel.setSortBy(sortBy,page++)
                    observeViewModel(movieViewModel)
                    loading = true
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData()
    {
        binding.swipeRefreshLayout.isRefreshing = true
        val service=DaggerMovieDbComponent.builder().contextModule(ContextModule(requireContext().applicationContext)).build()
        movieViewModel= MovieSortByViewModel(MovieDbApiApplication(),MovieRepository( service.getMovieDbService()),sortBy,1)
        observeViewModel(movieViewModel)
    }

    private fun observeViewModel(movieViewModel: MovieSortByViewModel) {
        movieViewModel.getObservableProject().observe(viewLifecycleOwner,{
            loading=false
            binding.swipeRefreshLayout.isRefreshing=false
            Log.e("change","change")
            if(it== null || it.results.isEmpty())
                binding.textViewText="Please check you Internet Connection and try Again."
            else
            {
                binding.isLoadingData = false
                movieListAdapter.setMovieList(it.results)
            }
        })
    }

    override fun onClick(movieid: Long) {
        val intent= Intent(requireActivity(),MovieDetailActivity::class.java)
        intent.putExtra(Constant.MOVIE_ID,movieid)
        requireActivity().startActivity(intent)
    }

    override fun onRefresh() {
        if (!NetworkUtil().getNetworkAvailable(requireContext())) {
            Toast.makeText(
                requireContext(),
                "Could not load latest Movies. Please turn on the Internet.",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.swipeRefreshLayout.isRefreshing=true
        movieViewModel.setSortBy(sortBy,1)
        observeViewModel(movieViewModel)

    }
}