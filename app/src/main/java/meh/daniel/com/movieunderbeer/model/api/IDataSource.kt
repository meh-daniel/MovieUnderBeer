package meh.daniel.com.movieunderbeer.model.api

import meh.daniel.com.movieunderbeer.entities.films.FilmData
import retrofit2.Response
import retrofit2.http.GET

interface IDataSource {

    @GET("films.json")
    suspend fun getFilm() : Response<FilmData>

}