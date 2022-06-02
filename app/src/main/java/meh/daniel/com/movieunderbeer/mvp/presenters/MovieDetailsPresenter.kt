package meh.daniel.com.movieunderbeer.mvp.presenters

import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.runBlocking
import meh.daniel.com.movieunderbeer.app.App
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.di.modules.ApiModule
import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
import meh.daniel.com.movieunderbeer.entities.films.Film
import meh.daniel.com.movieunderbeer.entities.films.FilmData
import meh.daniel.com.movieunderbeer.mvp.base.BasePresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieDetailsView
import moxy.InjectViewState
import retrofit2.Response

@InjectViewState
class MovieDetailsPresenter : BasePresenter<MovieDetailsView>() {

    override fun injectDependency() {
        App.instance.appComponent.inject(this)
    }

    fun backExit() : Boolean{
        router.exit()
        return false
    }


    fun start(id: Int) : Unit = runBlocking {
        viewState.init()

        val api = ApiModule().api(Constants.API_URL, ApiModule().gson())
        val repo = RepositoryModule().filmRepository(api)
        val response: Response<FilmData> = (Dispatchers.Default){
            repo.loadFilms()
        }
        val filmData : Film = (Dispatchers.Default){
            getListFilms(response, id)
        }
        viewState.loadFilm(filmData)
    }


    private fun getListFilms(response: Response<FilmData>, idFilm: Int) : Film{
        lateinit var filmData : Film

        if (response.isSuccessful) {
            val items = response.body()?.films

            if (items != null) {
                for (i in 0 until items.count()) {
                    val id = items[i].id
                    val localName = items[i].localizedName
                    val name = items[i].name
                    val year = items[i].year
                    val rating = items[i].rating
                    val imageUrl = if(items[i].imageUrl != null){
                        Constants.API_BASE_URL_IMAGES_PHONE + Uri.parse((items[i].imageUrl)).lastPathSegment.toString()
                    }else{
                        null
                    }
                    val description = items[i].description
                    val genres = items[i].genres

                    if(idFilm == id){
                        filmData =  Film(id = id, localizedName = localName, name = name,
                            year = year, rating = rating, imageUrl =  imageUrl, description = description, genres = genres)
                    }
                }
            }
        }

        return filmData
    }

}