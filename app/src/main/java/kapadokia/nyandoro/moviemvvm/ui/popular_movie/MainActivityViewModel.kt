package kapadokia.nyandoro.moviemvvm.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import kapadokia.nyandoro.moviemvvm.data.repository.NetworkState
import kapadokia.nyandoro.moviemvvm.data.vo.Movie

class MainActivityViewModel (private val movieRepository:MoviePageListRepository) :ViewModel() {

    // initialize composite disposable
    private val compositeDisposable = CompositeDisposable()

    // fetch the live movie pages list
    val moviePagedList: LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

    // get the network state by lazy
    val networkState :LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    // checking if our movie page list is empty or not
    fun listIsEmpty(): Boolean{
        return moviePagedList.value?.isEmpty()?:true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}