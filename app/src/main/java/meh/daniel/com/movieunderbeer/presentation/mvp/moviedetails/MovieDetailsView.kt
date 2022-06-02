package meh.daniel.com.movieunderbeer.presentation.mvp.moviedetails

import meh.daniel.com.movieunderbeer.domain.entities.films.Film
import meh.daniel.com.movieunderbeer.presentation.mvp.base.BaseView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface MovieDetailsView : BaseView {

    fun init()

    fun loadFilm(film: Film)

}