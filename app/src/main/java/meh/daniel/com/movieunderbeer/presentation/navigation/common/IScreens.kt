package meh.daniel.com.movieunderbeer.presentation.navigation.common

import com.github.terrakok.cicerone.Screen

interface IScreens {

    fun homeScreen() : Screen

    fun openFilm(dataFilm: Int) : Screen

}