package com.hakancevik.moviesapp.service

import com.hakancevik.moviesapp.model.Movie
import io.reactivex.Single
import retrofit2.http.GET

interface MovieAPI {

    //https://api.themoviedb.org/3/movie/upcoming?api_key=40c50a5fefc8ac8a8b73183d4c143413
    //https://api.themoviedb.org/3/movie/top_rated?api_key=40c50a5fefc8ac8a8b73183d4c143413
    //https://api.themoviedb.org/3/movie/popular?api_key=40c50a5fefc8ac8a8b73183d4c143413


    @GET("movie/upcoming?api_key=40c50a5fefc8ac8a8b73183d4c143413")
    fun getUpComingMovies(): Single<Movie>


    @GET("movie/top_rated?api_key=40c50a5fefc8ac8a8b73183d4c143413")
    fun getTopRatedMovies(): Single<Movie>

    @GET("movie/popular?api_key=40c50a5fefc8ac8a8b73183d4c143413")
    fun getPopularMovies(): Single<Movie>


}