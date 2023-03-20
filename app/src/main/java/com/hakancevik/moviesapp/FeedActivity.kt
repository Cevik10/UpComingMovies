package com.hakancevik.moviesapp


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayoutMediator
import com.hakancevik.moviesapp.adapter.ViewPagerAdapter
import com.hakancevik.moviesapp.model.Movie
import com.hakancevik.moviesapp.service.MovieAPIService
import com.hakancevik.moviesapp.util.visible
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {


    private val movieAPIService = MovieAPIService()
    private val compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = "Top Rated"
                }

                1 -> {
                    tab.text = "Coming Up"
                }
            }
        }.attach()


        getUpComingDataFromAPI()
        getPopularDataFromAPI()


    }

    private fun getUpComingDataFromAPI() {
        compositeDisposable.add(
            movieAPIService.getUpComingMoviesData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Movie>() {
                    override fun onSuccess(t: Movie) {
                        Log.d("system.out", "coming: " + t.results[0].original_title)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("system.out", e.toString())
                    }


                })
        )
    }




    private fun getPopularDataFromAPI() {
        compositeDisposable.add(
            movieAPIService.getPopularMoviesData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Movie>() {
                    override fun onSuccess(t: Movie) {
                        Log.d("system.out", "popular: " + t.results[0].original_title)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("system.out", e.toString())
                    }


                })
        )
    }


}

