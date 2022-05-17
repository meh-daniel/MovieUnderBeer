package meh.daniel.com.movieunderbeer.mvp.presenters

import android.util.Log
import androidx.constraintlayout.motion.utils.ViewState
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import meh.daniel.com.movieunderbeer.adapters.recycler.common.Item
import meh.daniel.com.movieunderbeer.app.App
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.di.modules.ApiModule
import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
import meh.daniel.com.movieunderbeer.model.entities.films.Film
import meh.daniel.com.movieunderbeer.model.entities.films.FilmData
import meh.daniel.com.movieunderbeer.mvp.base.BasePresenter
import meh.daniel.com.movieunderbeer.mvp.navigation.IScreens
import meh.daniel.com.movieunderbeer.mvp.view.MovieListView
import moxy.InjectViewState
import moxy.MvpPresenter
import okhttp3.Dispatcher
import retrofit2.Response
import javax.inject.Inject

@InjectViewState
class MovieListPresenter : BasePresenter<MovieListView>() {
    override fun injectDependency() {
        App.instance.appComponent.inject(this)
    }

    fun start(list: List<Item>){
        GlobalScope.launch {
            try {
                launch(Dispatchers.Main){
                    viewState.setupAdapter()
                    viewState.setData(list)
                }
            }catch (e:Exception){

            }
        }
    }


}