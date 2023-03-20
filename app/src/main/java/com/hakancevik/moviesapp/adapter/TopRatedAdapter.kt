package com.hakancevik.moviesapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hakancevik.moviesapp.R
import com.hakancevik.moviesapp.model.Movie
import com.hakancevik.moviesapp.util.downloadFromUrl
import com.hakancevik.moviesapp.util.placeHolderProgressBar
import com.hakancevik.moviesapp.view.TopRatedMoviesFragmentDirections
import kotlinx.android.synthetic.main.recycler_row_top_rated.view.*

class TopRatedAdapter(private val topRatedMoviesList: ArrayList<com.hakancevik.moviesapp.model.Result>) : RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>() {

    class TopRatedViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row_top_rated, parent, false)
        return TopRatedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return topRatedMoviesList.size
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {

        val movie = topRatedMoviesList[position]

        val rating = (movie.vote_average) / 2
        val url = "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movie.poster_path

        holder.view.recyclerRowTopRatedMovieName.text = movie.original_title
        holder.view.recyclerRowTopRatedRating.rating = rating.toFloat()
        holder.view.recyclerRowTopRatedImage.downloadFromUrl(url, placeHolderProgressBar(holder.view.context))


        holder.itemView.setOnClickListener {
            val action = TopRatedMoviesFragmentDirections.actionTopRatedMoviesFragmentToTopRatedDetailsFragment()
            Navigation.findNavController(holder.view).navigate(action)
        }


    }

    fun updateTopRatedMovieList(newTopRatedMovieList: List<com.hakancevik.moviesapp.model.Result>) {
        topRatedMoviesList.clear()
        topRatedMoviesList.addAll(newTopRatedMovieList)
        notifyDataSetChanged()
    }


}