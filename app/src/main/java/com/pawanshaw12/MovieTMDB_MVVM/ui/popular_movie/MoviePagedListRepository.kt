package com.pawanshaw12.MovieTMDB_MVVM.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.pawanshaw12.MovieTMDB_MVVM.data.api.POST_PER_PAGE
import com.pawanshaw12.MovieTMDB_MVVM.data.api.TheMovieDBInterface
import com.pawanshaw12.MovieTMDB_MVVM.data.repository.MovieDataSource
import com.pawanshaw12.MovieTMDB_MVVM.data.repository.MovieDataSourceFactory
import com.pawanshaw12.MovieTMDB_MVVM.data.repository.NetworkState
import com.pawanshaw12.MovieTMDB_MVVM.data.model.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListRepository (private val apiService : TheMovieDBInterface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList (compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState)
    }
}