package com.hakancevik.moviesapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hakancevik.moviesapp.R
import com.hakancevik.moviesapp.adapter.TopRatedAdapter
import com.hakancevik.moviesapp.viewmodel.TopRatedViewModel
import kotlinx.android.synthetic.main.fragment_top_rated_movies.*

class TopRatedMoviesFragment : Fragment() {

    private lateinit var viewModel: TopRatedViewModel
    private val topRatedAdapter = TopRatedAdapter(arrayListOf())


    companion object {
        fun newInstance() = TopRatedMoviesFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this)[TopRatedViewModel::class.java]


        //viewModel.refreshData()
        viewModel.getDataFromAPI(viewModel.page)


        topRatedListRecyclerView.layoutManager = GridLayoutManager(context, 2)
        topRatedListRecyclerView.adapter = topRatedAdapter

        observerLiveData()

        Log.d("system.out", "page : " + viewModel.page.toString())

        topRatedScrollView.setOnScrollChangeListener { v: NestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY ->

            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                viewModel.page++
                viewModel.topRatedPaginationLoading.value = true
                viewModel.getDataFromAPI(viewModel.page)
                Log.d("system.out", "page : " + viewModel.page.toString())
            }
        }

    }


    private fun observerLiveData() {


        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer {
            it?.let {
                topRatedListRecyclerView.visibility = View.VISIBLE
                topRatedAdapter.updateTopRatedMovieList(it)
            }
        })

        viewModel.topRatedError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    topRatedErrorText.visibility = View.VISIBLE
                    topRatedLoadingProgressBar.visibility = View.GONE
                } else {
                    topRatedErrorText.visibility = View.GONE

                }

            }
        })

        viewModel.topRatedLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    topRatedLoadingProgressBar.visibility = View.VISIBLE
                    topRatedScrollView.visibility = View.GONE
                    topRatedPaginationProgressBar.visibility = View.GONE
                    topRatedErrorText.visibility = View.GONE

                } else {
                    topRatedLoadingProgressBar.visibility = View.GONE
                    topRatedScrollView.visibility = View.VISIBLE
                    topRatedPaginationProgressBar.visibility = View.VISIBLE
                }
            }
        })

        viewModel.topRatedPaginationLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    topRatedPaginationProgressBar.visibility = View.VISIBLE

                } else {
                    topRatedPaginationProgressBar.visibility = View.GONE

                }

            }

        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


}