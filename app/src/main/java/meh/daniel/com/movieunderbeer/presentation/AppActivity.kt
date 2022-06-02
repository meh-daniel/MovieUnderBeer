package meh.daniel.com.movieunderbeer.presentation

import android.os.Bundle
import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.AppNavigator
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.app.App
import meh.daniel.com.movieunderbeer.databinding.ActivityAppBinding
import meh.daniel.com.movieunderbeer.presentation.navigation.common.IScreens
import moxy.MvpAppCompatActivity
import javax.inject.Inject

class AppActivity : MvpAppCompatActivity() {


    @Inject
    lateinit var navigatorHolder : NavigatorHolder
    @Inject
    lateinit var screens: IScreens
    @Inject
    lateinit var AppScreens: IScreens

    private lateinit var vb: ActivityAppBinding
    private val navigator : Navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
        vb = ActivityAppBinding.inflate(layoutInflater)
        setContentView(vb.root)
        if (savedInstanceState == null){
            navigator.applyCommands(arrayOf<Command>(Replace(AppScreens.homeScreen())))
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun injectDependency() {
        App.instance.appComponent.inject(this)
    }

}