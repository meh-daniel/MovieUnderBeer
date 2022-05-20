package meh.daniel.com.movieunderbeer.model.entities.films


import com.google.gson.annotations.SerializedName

data class FilmData(
    @SerializedName("films")
    val films: List<Film>?
)