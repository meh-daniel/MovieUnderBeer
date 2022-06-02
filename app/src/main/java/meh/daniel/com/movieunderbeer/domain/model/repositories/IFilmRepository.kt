package meh.daniel.com.movieunderbeer.domain.model.repositories

import meh.daniel.com.movieunderbeer.domain.entities.films.FilmData
import meh.daniel.com.movieunderbeer.domain.model.network.Result

interface IFilmRepository {

    suspend fun loadFilms(): Result<FilmData>

}