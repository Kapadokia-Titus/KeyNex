package kapadokia.nyandoro.moviemvvm.ui.single_movie_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kapadokia.nyandoro.moviemvvm.R
import kapadokia.nyandoro.moviemvvm.data.api.POSTER_BASE_URL
import kapadokia.nyandoro.moviemvvm.data.api.TheMovieDBClient
import kapadokia.nyandoro.moviemvvm.data.api.TheMovieDbInterface
import kapadokia.nyandoro.moviemvvm.data.repository.NetworkState
import kapadokia.nyandoro.moviemvvm.data.vo.MovieDetails
import kotlinx.android.synthetic.main.activity_single_movie.*
import java.text.NumberFormat
import java.util.*

class SingleMovie : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieRepository: MovieDetailsRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

        val movieId:Int  = intent.getIntExtra("id", 1)
        val apiService : TheMovieDbInterface = TheMovieDBClient.getClient()
        movieRepository = MovieDetailsRepository(apiService)

        // getting the view Model
        viewModel = getViewModel(movieId)

        // observing the movie detail, and if change happens we can update the view
        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })

        // observing the network state
        viewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (it== NetworkState.ERROR) View.VISIBLE else View.GONE
        })
    }

    fun bindUI(it :MovieDetails){
        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rating.text = it.rating.toString()
        movie_runtime.text = it.runtime.toString()
        movie_overview.text =it.overview

        // formatting the currency using the number format
        val formatCurrency:NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        movie_budget.text =formatCurrency.format(it.budget)
        movie_revenue.text =formatCurrency.format(it.revenue)

        // combining the poster path with poster base url to get the full image Url
        val moviePosterURL:String = POSTER_BASE_URL+it.posterPath
        // using glide to show poster into our imageView
        Log.d("image", "bindUI: image needed  "+moviePosterURL)
        Glide.with(this)
            .load(moviePosterURL)
            .error(R.drawable.error)
            .into(iv_movie_poster)
    }

    private fun getViewModel(movieId: Int):SingleMovieViewModel{
        return  ViewModelProviders.of(this, object : ViewModelProvider.Factory{
            override fun<T : ViewModel?> create(modelClass: Class<T>):T{
                return SingleMovieViewModel(movieRepository,movieId) as T
            }
        })[SingleMovieViewModel::class.java]
    }
}
