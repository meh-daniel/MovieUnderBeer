package meh.daniel.com.movieunderbeer.mvp.presenters

import com.github.terrakok.cicerone.Router
import meh.daniel.com.movieunderbeer.mvp.navigation.IScreens
import meh.daniel.com.movieunderbeer.mvp.view.MovieInfoView
import moxy.MvpPresenter
import javax.inject.Inject

class MovieInfoPresenter : MvpPresenter<MovieInfoView>() {



    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.homeScreen())
    }

    fun backClicked() {
        router.exit()
    }
}