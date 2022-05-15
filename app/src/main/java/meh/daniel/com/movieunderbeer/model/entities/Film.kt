package meh.daniel.com.movieunderbeer.model.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Film(
    @SerializedName("films")
    val films: List<FilmX>
)