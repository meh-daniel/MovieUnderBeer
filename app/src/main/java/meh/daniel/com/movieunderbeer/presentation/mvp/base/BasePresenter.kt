package meh.daniel.com.movieunderbeer.presentation.mvp.base

import com.github.terrakok.cicerone.Router
import meh.daniel.com.movieunderbeer.presentation.navigation.common.IScreens
import moxy.MvpPresenter
import javax.inject.Inject

abstract class BasePresenter<view: BaseView> : MvpPresenter<view>() {

    @Inject
    protected lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    abstract fun injectDependency()

}