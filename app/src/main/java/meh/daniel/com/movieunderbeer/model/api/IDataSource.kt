package meh.daniel.com.movieunderbeer.model.api

import meh.daniel.com.movieunderbeer.entities.films.FilmData
import meh.daniel.com.movieunderbeer.model.network.Result
import retrofit2.http.GET

interface IDataSource {

    @GET("films.json")
    suspend fun getFilm() : Result<FilmData>

}