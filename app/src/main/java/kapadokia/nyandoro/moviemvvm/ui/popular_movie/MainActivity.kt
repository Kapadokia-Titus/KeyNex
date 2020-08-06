package kapadokia.nyandoro.moviemvvm.ui.popular_movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kapadokia.nyandoro.moviemvvm.R
import kapadokia.nyandoro.moviemvvm.ui.single_movie_details.SingleMovie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener{
            val intent = Intent(this, SingleMovie::class.java)
            intent.putExtra("id", 672)
            this.startActivity(intent)
        }
    }
}