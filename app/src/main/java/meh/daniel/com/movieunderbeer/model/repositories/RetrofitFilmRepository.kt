package meh.daniel.com.movieunderbeer.model.repositories

import meh.daniel.com.movieunderbeer.model.api.IDataSource
import meh.daniel.com.movieunderbeer.model.entities.FilmData
import retrofit2.Response

class RetrofitFilmRepository(
    private val api: IDataSource
) : IFilmRepository {
    override suspend fun loadFilms(): Response<FilmData> {
        return api.getFilm()
    }
}