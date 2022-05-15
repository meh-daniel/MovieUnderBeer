package meh.daniel.com.movieunderbeer.adapter.common.films

import meh.daniel.com.movieunderbeer.adapter.common.Item

interface FilmsItem: Item {
    fun setTitle(text: String)
    fun loadPoster(url: String)
    fun setRating(countStar: Double)
}