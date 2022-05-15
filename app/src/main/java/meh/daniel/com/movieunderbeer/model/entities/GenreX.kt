package meh.daniel.com.movieunderbeer.model.entities

import com.google.gson.annotations.SerializedName
import meh.daniel.com.movieunderbeer.adapter.common.Item

data class GenreX (
    @SerializedName("genres")
    val genres: List<String>
    ) : Item