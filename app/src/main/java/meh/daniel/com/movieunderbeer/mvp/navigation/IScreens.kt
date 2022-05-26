package meh.daniel.com.movieunderbeer.mvp.navigation

import com.github.terrakok.cicerone.Screen
import meh.daniel.com.movieunderbeer.entities.films.Film

interface IScreens {
    fun homeScreen() : Screen
    fun openFilm() : Screen
}