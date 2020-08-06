package kapadokia.nyandoro.moviemvvm.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import kapadokia.nyandoro.moviemvvm.data.api.TheMovieDbInterface
import kapadokia.nyandoro.moviemvvm.data.vo.Movie

class MovieDataSourceFactory (private val apiService: TheMovieDbInterface, private val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int,Movie>() {

    val moviesLiveDataSource = MutableLiveData<MovieDataSource>()
    override fun create(): DataSource<Int, Movie> {

        // initialize the movie dataSource
        val movieDataSource =MovieDataSource(apiService, compositeDisposable)
        // posting the value of movie dataSource in mutable live data
        moviesLiveDataSource.postValue(movieDataSource)

        return movieDataSource
    }
}