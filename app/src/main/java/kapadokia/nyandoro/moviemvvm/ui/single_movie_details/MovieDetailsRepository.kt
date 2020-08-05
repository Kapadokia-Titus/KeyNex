package kapadokia.nyandoro.moviemvvm.ui.single_movie_details

import androidx.lifecycle.LiveData
import io.reactivex.disposables.CompositeDisposable
import kapadokia.nyandoro.moviemvvm.data.api.TheMovieDbInterface
import kapadokia.nyandoro.moviemvvm.data.repository.MovieDetailsNetworkDataSource
import kapadokia.nyandoro.moviemvvm.data.repository.NetworkState
import kapadokia.nyandoro.moviemvvm.data.vo.MovieDetails

class MovieDetailsRepository (private val apiService: TheMovieDbInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails(compositeDisposable: CompositeDisposable, movieId: Int):LiveData<MovieDetails>{
        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieDetailsResponse
    }


    fun getMovieDetailsNetworkState(): LiveData<NetworkState>{
        return movieDetailsNetworkDataSource.networkState
    }
}