package meh.daniel.com.movieunderbeer.mvp.presenters.activity.host

import android.widget.Toast
import com.github.terrakok.cicerone.Forward
import com.github.terrakok.cicerone.Router
import meh.daniel.com.movieunderbeer.mvp.navigation.IScreens
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

//
//    override fun onFirstViewAttach() {
//        super.onFirstViewAttach()
//
//    }

    fun newScreen(){
        router.newRootScreen(screens.homeScreen())
    }

    fun backClicked() {
        router.exit()
    }

}