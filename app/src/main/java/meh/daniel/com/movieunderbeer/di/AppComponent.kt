package meh.daniel.com.movieunderbeer.di

import dagger.Component
import meh.daniel.com.movieunderbeer.di.modules.*
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieInfoPresenter
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieListPresenter
import meh.daniel.com.movieunderbeer.ui.AppActivity
import meh.daniel.com.movieunderbeer.ui.base.BaseFragment
import meh.daniel.com.movieunderbeer.ui.fragments.MovieDetailsFragment
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
    fun inject(baseFragment: BaseFragment?)

    fun inject(appActivity: AppActivity)

    fun inject(movieInfoPresenter: MovieInfoPresenter)
    fun inject(movieInfoPresenter: MovieDetailsFragment)

    fun inject(movieListPresenter: MovieListPresenter)
    fun inject(movieListPresenter: MovieListFragment)
}