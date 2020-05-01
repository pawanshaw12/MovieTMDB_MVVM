package com.pawanshaw12.MovieTMDB_MVVM.data.api

import com.pawanshaw12.MovieTMDB_MVVM.data.model.MovieDetails
import com.pawanshaw12.MovieTMDB_MVVM.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    // https://api.themoviedb.org/3/movie/popular?api_key=a490b78628b90a02c0089a57ee5f413b&page=1
    // https://api.themoviedb.org/3/movie/299534?api_key=a490b78628b90a02c0089a57ee5f413b
    // https://api.themoviedb.org/3/

    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}