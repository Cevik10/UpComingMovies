package com.hakancevik.moviesapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hakancevik.moviesapp.model.Movie
import com.hakancevik.moviesapp.service.MovieAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class TopRatedViewModel(application: Application) : BaseViewModel(application) {

    val topRatedMovies = MutableLiveData<List<com.hakancevik.moviesapp.model.Result>>()
    val topRatedError = MutableLiveData<Boolean>()
    val topRatedLoading = MutableLiveData<Boolean>()
    val topRatedPaginationLoading = MutableLiveData<Boolean>()

    private val movieAPIService = MovieAPIService()
    private val compositeDisposable = CompositeDisposable()

    var page = 1


    fun refreshData() {
        getDataFromAPI(page)
    }

    fun getDataFromAPI(page: Int) {

        if (page == 1) {
            topRatedLoading.value = true
        }

        compositeDisposable.add(
            movieAPIService.getTopRatedMoviesData(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Movie>() {
                    override fun onSuccess(t: Movie) {

                        if (page == 1) {
                            topRatedMovies.value = t.results
                        } else {
                            topRatedMovies.value = topRatedMovies.value?.plus(t.results)
                        }
                        topRatedLoading.value = false
                        topRatedError.value = false
                        topRatedPaginationLoading.value = false

                    }

                    override fun onError(e: Throwable) {
                        Log.d("system.out", e.toString())
                        topRatedError.value = true
                    }


                })
        )
    }


}