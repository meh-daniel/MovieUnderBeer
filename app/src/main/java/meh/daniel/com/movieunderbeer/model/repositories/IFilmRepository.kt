package meh.daniel.com.movieunderbeer.model.repositories

import meh.daniel.com.movieunderbeer.model.entities.FilmData
import retrofit2.Response


interface IFilmRepository {
    suspend fun loadFilms(): Response<FilmData>
}