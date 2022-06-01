package meh.daniel.com.movieunderbeer.mvp.navigation

import com.github.terrakok.cicerone.Screen

interface IScreens {

    fun homeScreen() : Screen

    fun openFilm(dataFilm: Int) : Screen

}