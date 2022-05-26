package meh.daniel.com.movieunderbeer.mvp.presenters

import meh.daniel.com.movieunderbeer.app.App
import meh.daniel.com.movieunderbeer.mvp.base.BasePresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieInfoView
import moxy.InjectViewState

@InjectViewState
class MovieInfoPresenter : BasePresenter<MovieInfoView>() {

    fun backClicked() {
        router.exit()
    }

    override fun injectDependency() {
        App.instance.appComponent.inject(this)
    }
}