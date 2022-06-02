package meh.daniel.com.movieunderbeer.model.repositories

import meh.daniel.com.movieunderbeer.model.api.IDataSource
import meh.daniel.com.movieunderbeer.entities.films.FilmData
import meh.daniel.com.movieunderbeer.model.network.Result

class RetrofitFilmRepository(
    private val api: IDataSource
) : IFilmRepository {

    override suspend fun loadFilms(): Result<FilmData> {
        return api.getFilm()
    }

}