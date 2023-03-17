package com.hakancevik.moviesapp.service

import com.hakancevik.moviesapp.model.Movie
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieAPIService {

    //https://api.themoviedb.org/3/movie/upcoming?api_key=40c50a5fefc8ac8a8b73183d4c143413

    private val BASE_URL = "https://api.themoviedb.org/3/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    fun getUpComingMoviesData(): Single<Movie> {
        return api.getUpComingMovies()
    }

    fun getTopRatedMoviesData(): Single<Movie> {
        return api.getTopRatedMovies()
    }

    fun getPopularMoviesData(): Single<Movie> {
        return api.getPopularMovies()
    }


}