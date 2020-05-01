package com.pawanshaw12.MovieTMDB_MVVM.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.pawanshaw12.MovieTMDB_MVVM.data.repository.NetworkState
import com.pawanshaw12.MovieTMDB_MVVM.data.model.Movie
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(private val movieRepository : MoviePagedListRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val  moviePagedList : LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

    val  networkState : LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}