package meh.daniel.com.movieunderbeer.adapters.list.films

import meh.daniel.com.movieunderbeer.adapters.list.IItemView

interface IFilmsItemView: IItemView {
    fun setTitle(text: String)
    fun loadPoster(url: String)
    fun setRating(countStar: Double)
}