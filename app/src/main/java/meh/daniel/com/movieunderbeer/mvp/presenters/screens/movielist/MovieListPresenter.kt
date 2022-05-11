package meh.daniel.com.movieunderbeer.mvp.presenters.screens.movielist

import com.github.terrakok.cicerone.Router
import meh.daniel.com.movieunderbeer.mvp.navigation.IScreens
import moxy.MvpPresenter
import javax.inject.Inject

class MovieListPresenter : MvpPresenter<MovieListView>() {


    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

}