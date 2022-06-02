package meh.daniel.com.movieunderbeer.di

import dagger.Component
import meh.daniel.com.movieunderbeer.di.modules.*
import meh.daniel.com.movieunderbeer.presentation.mvp.moviedetails.MovieDetailsPresenter
import meh.daniel.com.movieunderbeer.presentation.mvp.movielist.MovieListPresenter
import meh.daniel.com.movieunderbeer.presentation.AppActivity
import meh.daniel.com.movieunderbeer.presentation.ui.moviedetails.MovieDetailsFragment
import meh.daniel.com.movieunderbeer.presentation.ui.movielist.MovieListFragment
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

    fun inject(appActivity: AppActivity)

    fun inject(movieDetailsPresenter: MovieDetailsPresenter)
    fun inject(movieInfoPresenter: MovieDetailsFragment)

    fun inject(movieListPresenter: MovieListPresenter)
    fun inject(movieListPresenter: MovieListFragment)

}