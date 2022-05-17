package meh.daniel.com.movieunderbeer.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.terrakok.cicerone.Router
import meh.daniel.com.movieunderbeer.app.App
import meh.daniel.com.movieunderbeer.di.AppComponent
import moxy.MvpAppCompatFragment
import javax.inject.Inject

abstract class BaseFragment : MvpAppCompatFragment(){

    @Inject
    protected lateinit var router: Router

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.appComponent.inject(this)
    }

    abstract fun injectDependency()


}