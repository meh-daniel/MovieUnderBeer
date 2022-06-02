package meh.daniel.com.movieunderbeer.domain.model.repositories

import meh.daniel.com.movieunderbeer.domain.model.api.IDataSource
import meh.daniel.com.movieunderbeer.domain.entities.films.FilmData
import meh.daniel.com.movieunderbeer.domain.model.network.Result

class RetrofitFilmRepository(
    private val api: IDataSource
) : IFilmRepository {

    override suspend fun loadFilms(): Result<FilmData> {
        return api.getFilm()
    }

}