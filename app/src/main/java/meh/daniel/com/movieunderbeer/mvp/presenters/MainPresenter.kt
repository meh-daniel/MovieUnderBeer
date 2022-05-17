package meh.daniel.com.movieunderbeer.mvp.presenters

import com.github.terrakok.cicerone.Router
import meh.daniel.com.movieunderbeer.mvp.base.BasePresenter
import meh.daniel.com.movieunderbeer.mvp.navigation.IScreens
import meh.daniel.com.movieunderbeer.mvp.view.MainView
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter : BasePresenter<MainView>() {
    override fun injectDependency() {
        TODO("Not yet implemented")
    }


}