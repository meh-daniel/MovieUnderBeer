package meh.daniel.com.movieunderbeer.mvp.view

import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.mvp.base.BaseView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface MovieListView : BaseView {

    fun setupAdapter(dataList : MutableList<Item>)

//    fun setData(dataList : MutableList<HasStringId>)

}