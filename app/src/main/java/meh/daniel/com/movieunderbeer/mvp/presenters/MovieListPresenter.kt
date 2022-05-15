package meh.daniel.com.movieunderbeer.mvp.presenters

import com.github.terrakok.cicerone.Router
import meh.daniel.com.movieunderbeer.mvp.navigation.IScreens
import meh.daniel.com.movieunderbeer.mvp.view.MovieListView
import moxy.MvpPresenter
import javax.inject.Inject

class MovieListPresenter : MvpPresenter<MovieListView>() {


    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

}