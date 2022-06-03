package meh.daniel.com.movieunderbeer.presentation.mvp.moviedetails

import meh.daniel.com.movieunderbeer.domain.entities.films.Film
import meh.daniel.com.movieunderbeer.presentation.mvp.base.BaseView
import moxy.viewstate.strategy.*

@StateStrategyType(AddToEndStrategy::class)
interface MovieDetailsView : BaseView {

    fun init()

    fun loadFilm(film: Film)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(message: String)

}