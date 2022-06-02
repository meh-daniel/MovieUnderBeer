package meh.daniel.com.movieunderbeer.presentation.mvp.movielist

import meh.daniel.com.movieunderbeer.presentation.adapter.common.Item
import meh.daniel.com.movieunderbeer.domain.entities.films.Film
import meh.daniel.com.movieunderbeer.domain.entities.recyclerfeed.FeedGenre
import meh.daniel.com.movieunderbeer.presentation.mvp.base.BaseView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface MovieListView : BaseView {

    fun init()

    fun setData(dataList: MutableList<Item>)

    fun onListFilmClick(film : Film)

    fun onListGenreClick(genre: FeedGenre)

    fun showError(message: String)

}