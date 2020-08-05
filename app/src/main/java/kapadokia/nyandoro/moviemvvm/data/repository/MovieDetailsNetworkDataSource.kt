package kapadokia.nyandoro.moviemvvm.data.repository;

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kapadokia.nyandoro.moviemvvm.data.api.TheMovieDbInterface
import kapadokia.nyandoro.moviemvvm.vo.MovieDetails

// here we will call our Api using RxJava and our Api will return the movie details
// then we'll assign the movie details in a live data
// CompositeDisposable is an RxJava class that we can use to dispose our RxJava Thread

class MovieDetailsNetworkDataSource(private val apiService:TheMovieDbInterface, private val compositeDisposable: CompositeDisposable) {

    // we will use mutable live data because liveData is not mutable in nature
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState:LiveData<NetworkState>
        get() = _networkState  // with this, there is no need to implement get function to get network state

    private val _downloadedMovieDetailsResponse = MutableLiveData<MovieDetails>()
    val downloadedMovieDetailsResponse:LiveData<MovieDetails>
        get()= _downloadedMovieDetailsResponse


    fun fetchMovieDetails(movieId: Int){

        //post the value of network state loading to network state mutable live data
        _networkState.postValue(NetworkState.LOADING)

        // using RxJava Thread to make Network Calls using a try catch block
        try {
            // we want this thread to be disposable,  that's why we will add it to a composite disposable
            compositeDisposable.add(
                apiService.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe({

                        // success
                        _downloadedMovieDetailsResponse.postValue(it)
                        _networkState.postValue(NetworkState.LOADED)
                    }, {

                        // error
                        _networkState.postValue(NetworkState.ERROR)
                        Log.e("MovieDetailsNetworkDataSource", it.message )
                    })
            )

        }catch (e:Exception){
            Log.e("MovieDetailsNetworkDataSource", e.message )
        }

    }
}
