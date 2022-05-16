package meh.daniel.com.movieunderbeer.model.entities


import com.google.gson.annotations.SerializedName
import meh.daniel.com.movieunderbeer.adapters.recycler.common.Item

data class FilmData(
    @SerializedName("films")
    val films: List<Film>?
) : Item