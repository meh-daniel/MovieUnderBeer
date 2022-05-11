package meh.daniel.com.movieunderbeer.di

import dagger.Component
import meh.daniel.com.movieunderbeer.di.modules.AppModule
import meh.daniel.com.movieunderbeer.di.modules.CiceroneModule
import meh.daniel.com.movieunderbeer.mvp.presenters.activity.host.MainPresenter
import meh.daniel.com.movieunderbeer.mvp.presenters.screens.movieinfo.MovieInfoPresenter
import meh.daniel.com.movieunderbeer.mvp.presenters.screens.movielist.MovieListPresenter
import meh.daniel.com.movieunderbeer.ui.activity.main.MainActivity
import meh.daniel.com.movieunderbeer.ui.fragments.MovieInfoFragment
import meh.daniel.com.movieunderbeer.ui.fragments.MovieListFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
            CiceroneModule::class,
            AppModule::class
        ]
)
interface AppComponent {
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainPresenter: MainActivity)

    fun inject(movieInfoPresenter: MovieInfoPresenter)
    fun inject(movieInfoPresenter: MovieInfoFragment)

    fun inject(movieListPresenter: MovieListPresenter)
    fun inject(movieListPresenter: MovieListFragment)
}