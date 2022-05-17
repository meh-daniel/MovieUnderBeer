package meh.daniel.com.movieunderbeer.ui.activity.main

import android.os.Bundle
import android.util.Log
import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.AppNavigator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.app.App
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.databinding.ActivityMainBinding
import meh.daniel.com.movieunderbeer.di.modules.ApiModule
import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
import meh.daniel.com.movieunderbeer.mvp.navigation.IScreens
import meh.daniel.com.movieunderbeer.mvp.presenters.MainPresenter
import meh.daniel.com.movieunderbeer.mvp.view.MainView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var vb: ActivityMainBinding
    private val navigator : Navigator = object : AppNavigator(this, R.id.main_container){
        override fun applyCommand(command: Command) {
            super.applyCommand(command)
            supportFragmentManager.executePendingTransactions()
        }
    }

    @Inject
    lateinit var navigatorHolder : NavigatorHolder
    @Inject
    lateinit var screens: IScreens
    @Inject
    lateinit var AppScreens: IScreens

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
        if (savedInstanceState == null)
            navigator.applyCommands(arrayOf<Command>(Replace(AppScreens.homeScreen())))
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
    }


    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}