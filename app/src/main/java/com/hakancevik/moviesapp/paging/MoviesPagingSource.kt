//package com.hakancevik.moviesapp.paging
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.hakancevik.moviesapp.model.Movie
//import com.hakancevik.moviesapp.service.MovieAPI
//
//class MoviesPagingSource(private val movieAPI: MovieAPI) : PagingSource<Int, Movie>() {
//
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
//        val page = params.key ?: STARTING_PAGE_INDEX
//
//        return try {
//            val response = movieAPI.getTopRatedMovies(page, params.loadSize)
//            LoadResult.Page(
//                data = response,
//                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
//                nextKey = if (response.results.isEmpty()) null else page.plus(1)
//            )
//        } catch (e: Exception) {
//            return LoadResult.Error(e)
//        }
//
//
//    }
//
//
//    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//
//    companion object {
//        private const val STARTING_PAGE_INDEX = 1
//    }
//
//
//}