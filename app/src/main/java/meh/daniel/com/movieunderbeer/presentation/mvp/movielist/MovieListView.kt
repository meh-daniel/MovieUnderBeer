package meh.daniel.com.movieunderbeer.presentation.mvp.movielist

import meh.daniel.com.movieunderbeer.presentation.adapter.common.Item
import meh.daniel.com.movieunderbeer.domain.entities.films.Film
import meh.daniel.com.movieunderbeer.domain.entities.recyclerfeed.FeedGenre
import meh.daniel.com.movieunderbeer.presentation.mvp.base.BaseView
import moxy.viewstate.strategy.*

@StateStrategyType(AddToEndStrategy::class)
interface MovieListView : BaseView {

    fun init()

    fun setData(dataList: MutableList<Item>)

    fun onListFilmClick(film : Film)

    fun onListGenreClick(genre: FeedGenre)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(message: String)

}