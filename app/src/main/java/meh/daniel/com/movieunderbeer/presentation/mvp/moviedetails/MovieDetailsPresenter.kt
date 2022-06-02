package meh.daniel.com.movieunderbeer.presentation.mvp.moviedetails

import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.runBlocking
import meh.daniel.com.movieunderbeer.app.App
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.di.modules.ApiModule
import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
import meh.daniel.com.movieunderbeer.domain.entities.films.Film
import meh.daniel.com.movieunderbeer.domain.entities.films.FilmData
import meh.daniel.com.movieunderbeer.domain.model.network.Result
import meh.daniel.com.movieunderbeer.domain.model.network.asSuccess
import meh.daniel.com.movieunderbeer.domain.model.network.isSuccess
import meh.daniel.com.movieunderbeer.presentation.mvp.base.BasePresenter
import moxy.InjectViewState

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
        val response: Result<FilmData> = (Dispatchers.Default){
            repo.loadFilms()
        }

        if(response.isSuccess()){
            val filmData : Film = (Dispatchers.Default){
                getListFilms(response, id)
            }
            viewState.loadFilm(filmData)
        }else{
            Log.d("xxx:", "${response.toString()}")
        }

    }

    private fun getListFilms(response: Result<FilmData>, idFilm: Int) : Film{
        lateinit var filmData : Film

        val getItems = response.asSuccess().value
        val items = getItems.films

        if (items != null) {
            for (i in items.indices) {
                val id = items[i].id
                val localName = items[i].localizedName
                val name = items[i].name
                val year = items[i].year
                val rating = items[i].rating
                val imageUrl = if (items[i].imageUrl != null) {
                    Constants.API_BASE_URL_IMAGES_PHONE + Uri.parse((items[i].imageUrl)).lastPathSegment.toString()
                } else {
                    null
                }
                val description = items[i].description
                val genres = items[i].genres

                if(idFilm == id){
                    filmData = (
                        Film(
                            id = id,
                            localizedName = localName,
                            name = name,
                            year = year,
                            rating = rating,
                            imageUrl = imageUrl,
                            description = description,
                            genres = genres
                        )
                    )
                }
            }
        }

        return filmData
    }
}