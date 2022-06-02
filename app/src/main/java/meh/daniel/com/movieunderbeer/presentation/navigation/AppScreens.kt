package meh.daniel.com.movieunderbeer.presentation.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import meh.daniel.com.movieunderbeer.presentation.navigation.common.IScreens
import meh.daniel.com.movieunderbeer.presentation.ui.moviedetails.MovieDetailsFragment
import meh.daniel.com.movieunderbeer.presentation.ui.movielist.MovieListFragment

class AppScreens : IScreens {

    override fun homeScreen(): Screen = FragmentScreen{ MovieListFragment() }

    override fun openFilm(dataFilm: Int): Screen = FragmentScreen { MovieDetailsFragment.newInstance(dataFilm)}

}