package meh.daniel.com.movieunderbeer.model.repositories

import meh.daniel.com.movieunderbeer.entities.films.FilmData
import meh.daniel.com.movieunderbeer.model.network.Result

interface IFilmRepository {

    suspend fun loadFilms(): Result<FilmData>

}