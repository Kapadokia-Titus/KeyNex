package kapadokia.nyandoro.moviemvvm.data.vo


import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    @SerializedName("original_language")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String
)