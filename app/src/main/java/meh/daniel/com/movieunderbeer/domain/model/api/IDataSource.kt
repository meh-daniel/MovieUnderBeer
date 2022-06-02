package meh.daniel.com.movieunderbeer.domain.model.api

import meh.daniel.com.movieunderbeer.domain.entities.films.FilmData
import meh.daniel.com.movieunderbeer.domain.model.network.Result
import retrofit2.http.GET

interface IDataSource {

    @GET("films.json")
    suspend fun getFilm() : Result<FilmData>

}