package meh.daniel.com.movieunderbeer.mvp.view

import meh.daniel.com.movieunderbeer.mvp.base.BaseView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface MovieInfoView : BaseView {
}