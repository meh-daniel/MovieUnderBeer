package meh.daniel.com.movieunderbeer.mvp.presenters

import meh.daniel.com.movieunderbeer.app.App
import meh.daniel.com.movieunderbeer.mvp.base.BasePresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieDetailsView
import moxy.InjectViewState

@InjectViewState
class MovieDetailsPresenter : BasePresenter<MovieDetailsView>() {

    override fun injectDependency() {
        App.instance.appComponent.inject(this)
    }

    fun backExit(){
        router.exit()
    }
}