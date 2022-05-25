package meh.daniel.com.movieunderbeer.di

import dagger.Component
import meh.daniel.com.movieunderbeer.di.modules.*
import meh.daniel.com.movieunderbeer.mvp.presenters.MainPresenter
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieInfoPresenter
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieListPresenter
import meh.daniel.com.movieunderbeer.ui.activity.main.MainActivity
import meh.daniel.com.movieunderbeer.ui.base.BaseActivity
import meh.daniel.com.movieunderbeer.ui.base.BaseFragment
import meh.daniel.com.movieunderbeer.ui.fragments.MovieInfoFragment
import meh.daniel.com.movieunderbeer.ui.fragments.MovieListFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
            AppModule::class,
            CiceroneModule::class,
            ApiModule::class,
            RepositoryModule::class
        ]
)
interface AppComponent {
    fun inject(baseActivity: BaseActivity?)
    fun inject(baseFragment: BaseFragment?)

    fun inject(mainPresenter: MainPresenter)
    fun inject(mainPresenter: MainActivity)

    fun inject(movieInfoPresenter: MovieInfoPresenter)
    fun inject(movieInfoPresenter: MovieInfoFragment)

    fun inject(movieListPresenter: MovieListPresenter)
    fun inject(movieListPresenter: MovieListFragment)
}