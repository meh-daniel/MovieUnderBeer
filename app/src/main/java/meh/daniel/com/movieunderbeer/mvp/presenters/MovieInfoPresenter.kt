package meh.daniel.com.movieunderbeer.mvp.presenters

import com.github.terrakok.cicerone.Router
import meh.daniel.com.movieunderbeer.mvp.base.BasePresenter
import meh.daniel.com.movieunderbeer.mvp.navigation.IScreens
import meh.daniel.com.movieunderbeer.mvp.view.MovieInfoView
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject
@InjectViewState
class MovieInfoPresenter : BasePresenter<MovieInfoView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.homeScreen())
    }

    fun backClicked() {
        router.exit()
    }

    override fun injectDependency() {
        TODO("Not yet implemented")
    }
}