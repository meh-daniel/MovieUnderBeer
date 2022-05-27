package meh.daniel.com.movieunderbeer.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import meh.daniel.com.movieunderbeer.mvp.navigation.IScreens
import meh.daniel.com.movieunderbeer.ui.fragments.MovieDetailsFragment
import meh.daniel.com.movieunderbeer.ui.fragments.MovieListFragment

class AppScreens : IScreens {

    override fun homeScreen(): Screen = FragmentScreen{ MovieListFragment()}

    override fun openFilm(): Screen = FragmentScreen {MovieDetailsFragment()}

}