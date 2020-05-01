package com.pawanshaw12.MovieTMDB_MVVM.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.pawanshaw12.MovieTMDB_MVVM.data.api.TheMovieDBInterface
import com.pawanshaw12.MovieTMDB_MVVM.data.repository.MovieDetailsNetworkDataSource
import com.pawanshaw12.MovieTMDB_MVVM.data.repository.NetworkState
import com.pawanshaw12.MovieTMDB_MVVM.data.model.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository (private val apiService : TheMovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetails> {

        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse

    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }



}