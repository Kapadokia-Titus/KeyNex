package kapadokia.nyandoro.moviemvvm.ui.single_movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kapadokia.nyandoro.moviemvvm.data.repository.NetworkState
import kapadokia.nyandoro.moviemvvm.data.vo.MovieDetails

class SingleMovieViewModel(private val movieDetailsRepository: MovieDetailsRepository, movieId: Int) : ViewModel(){

    // initialising the composite disposable
    private val compositeDisposable = CompositeDisposable()

    // fetching single movie details
    // we fetch by lazy so that we get the movies when we need it, not when the class is initialised
    // it is good for better performance
    val movieDetails: LiveData<MovieDetails> by lazy {
        movieDetailsRepository.fetchSingleMovieDetails(compositeDisposable, movieId)
    }

    // we will also get movie details network state by lazy

    val networkState: LiveData<NetworkState> by lazy {
        movieDetailsRepository.getMovieDetailsNetworkState()
    }

    override fun onCleared(){
        super.onCleared()
        compositeDisposable.dispose()
    }
}