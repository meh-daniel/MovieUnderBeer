package meh.daniel.com.movieunderbeer.domain.entities.films


import com.google.gson.annotations.SerializedName

data class FilmData(
    @SerializedName("films")
    val films: List<Film>?
)