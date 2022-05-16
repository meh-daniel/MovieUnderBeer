package meh.daniel.com.movieunderbeer.adapters.recycler.common.films.genres

import meh.daniel.com.movieunderbeer.adapters.recycler.common.Item

interface GenresItem : Item {
    fun setTitle(text: String)
}