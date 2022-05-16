package meh.daniel.com.movieunderbeer.adapters.recycler.common.films

import meh.daniel.com.movieunderbeer.adapters.recycler.common.Item

interface FilmsItem: Item {
    fun setTitle(text: String)
    fun loadPoster(url: String)
    fun setRating(countStar: Double)
}