package kapadokia.nyandoro.moviemvvm.data.repository

import androidx.paging.PageKeyedDataSource
import kapadokia.nyandoro.moviemvvm.data.vo.Movie

class MovieDataSource : PageKeyedDataSource<Int, Movie>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        
    }
}