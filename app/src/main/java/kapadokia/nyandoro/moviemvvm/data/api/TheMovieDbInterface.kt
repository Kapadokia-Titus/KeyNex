package kapadokia.nyandoro.moviemvvm.data.api

import io.reactivex.Single
import kapadokia.nyandoro.moviemvvm.data.vo.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbInterface {

    // https://api.themoviedb.org/3/movie/724089?api_key=db7f024b088bcaa6db06ba9bf9e4e544
    // https://api.themoviedb.org/3/movie/popular?api_key=db7f024b088bcaa6db06ba9bf9e4e544
    // https://api.themoviedb.org/3/


    // a function to get movie details
    // Single - is one type of observables
    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id:Int): Single<MovieDetails>

}