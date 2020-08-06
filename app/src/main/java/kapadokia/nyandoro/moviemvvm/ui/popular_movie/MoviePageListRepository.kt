package kapadokia.nyandoro.moviemvvm.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import kapadokia.nyandoro.moviemvvm.data.api.POST_PER_PAGE
import kapadokia.nyandoro.moviemvvm.data.api.TheMovieDbInterface
import kapadokia.nyandoro.moviemvvm.data.repository.MovieDataSource
import kapadokia.nyandoro.moviemvvm.data.repository.MovieDataSourceFactory
import kapadokia.nyandoro.moviemvvm.data.repository.NetworkState
import kapadokia.nyandoro.moviemvvm.data.vo.Movie

class MoviePageListRepository (private val apiService:TheMovieDbInterface) {
    lateinit var moviePageList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList(compositeDisposable: CompositeDisposable) :LiveData<PagedList<Movie>>{
        moviesDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config:PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        // initializing live page list builder
        moviePageList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePageList
    }

    // getting the network state
    fun getNetworkState():LiveData<NetworkState>{
        // access the network state from the movie datasource by using transformation switch map

        return Transformations.switchMap<MovieDataSource, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState)
    }
}