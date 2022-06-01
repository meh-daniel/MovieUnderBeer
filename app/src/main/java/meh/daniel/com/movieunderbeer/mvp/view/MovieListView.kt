package meh.daniel.com.movieunderbeer.mvp.view

import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.entities.films.Film
import meh.daniel.com.movieunderbeer.entities.recyclerfeed.FeedGenre
import meh.daniel.com.movieunderbeer.mvp.base.BaseView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface MovieListView : BaseView {

    fun init()

    fun setData(dataList: MutableList<Item>)

    fun onListFilmClick(film : Film)

    fun onListGenreClick(genre: FeedGenre)

}