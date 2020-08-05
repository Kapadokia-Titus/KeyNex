package kapadokia.nyandoro.moviemvvm.vo


import com.google.gson.annotations.SerializedName

data class BelongsToCollection(
    @SerializedName("backdrop_path")
    val backdropPath: Any,
    val id: Int,
    val name: String,
    @SerializedName("poster_path")
    val posterPath: Any
)