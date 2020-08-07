package kapadokia.nyandoro.moviemvvm.ui.popular_movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kapadokia.nyandoro.moviemvvm.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    lateinit var movieRepository: MoviePageListRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    // get
}