package meh.daniel.com.movieunderbeer.presentation.mvp.movielist

import android.net.Uri
import kotlinx.coroutines.*
import meh.daniel.com.movieunderbeer.presentation.adapter.common.Item
import meh.daniel.com.movieunderbeer.app.App
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.di.modules.ApiModule
import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
import meh.daniel.com.movieunderbeer.domain.entities.films.Film
import meh.daniel.com.movieunderbeer.domain.entities.films.FilmData
import meh.daniel.com.movieunderbeer.domain.entities.recyclerfeed.FeedGenre
import meh.daniel.com.movieunderbeer.domain.entities.recyclerfeed.FeedGenreGroup
import meh.daniel.com.movieunderbeer.domain.entities.recyclerfeed.FeedHeader
import meh.daniel.com.movieunderbeer.domain.model.network.Result
import meh.daniel.com.movieunderbeer.domain.model.network.asSuccess
import meh.daniel.com.movieunderbeer.domain.model.network.isSuccess
import meh.daniel.com.movieunderbeer.presentation.mvp.base.BasePresenter
import moxy.InjectViewState

@InjectViewState
class MovieListPresenter : BasePresenter<MovieListView>() {

    override fun injectDependency() {
        App.instance.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        getMovie()
    }

    fun getMovieBySelect(genre: FeedGenre){
        if (genre.title == "все"){
            getMovie()
        }else{
            getMovieByGenre(genre)
        }
    }
    var lastFilmId : Int? = null
    fun openFilm(film : Film): Unit = runBlocking {
        val api = ApiModule().api(Constants.API_URL, ApiModule().gson())
        val repo = RepositoryModule().filmRepository(api)
        val response: Result<FilmData> = (Dispatchers.Default){
            repo.loadFilms()
        }

        if(response.isSuccess()){
            if ( (lastFilmId == null) || (lastFilmId != film.id) ){
                film.id?.let {
                    lastFilmId = film.id.toInt()
                    router.navigateTo(screens.openFilm(film.id))
                }
            }
        }else{
            viewState.showError(response.toString())
        }
    }

    private fun getMovie() : Unit = runBlocking {

        val api = ApiModule().api(Constants.API_URL, ApiModule().gson())
        val repo = RepositoryModule().filmRepository(api)
        val response: Result<FilmData> = (Dispatchers.Default){
            repo.loadFilms()
        }

        if(response.isSuccess()){

            val data = mutableListOf<Item>()

            val listHeaderGenre: MutableList<Item> = (Dispatchers.Default){
                getHeader("Жанры")
            }
            val listGenre: MutableList<Item> = (Dispatchers.Default){
                getListGenreGroup(response)
            }
            val listHeaderFilm: MutableList<Item> = (Dispatchers.Default){
                getHeader("Фильмы")
            }
            val listFilm: MutableList<Item> = (Dispatchers.Default){
                getListFilms(response)
            }

            data.addAll(listHeaderGenre)
            data.addAll(listGenre)
            data.addAll(listHeaderFilm)
            data.addAll(listFilm)

            viewState.setData(data)

        } else{
            viewState.showError(response.toString())
        }
    }

    private fun getMovieByGenre(genre: FeedGenre) : Unit = runBlocking {

        val api = ApiModule().api(Constants.API_URL, ApiModule().gson())
        val repo = RepositoryModule().filmRepository(api)
        val response: Result<FilmData> = (Dispatchers.Default){
            repo.loadFilms()
        }

        if(response.isSuccess()){
            val data = mutableListOf<Item>()

            val listHeaderGenre: MutableList<Item> = (Dispatchers.Default){
                getHeader("Жанры")
            }
            val listGenre: MutableList<Item> = (Dispatchers.Default){
                getListGenreGroup(response)
            }
            val listHeaderFilm: MutableList<Item> = (Dispatchers.Default){
                getHeader("Фильмы")
            }
            val listFilm: MutableList<Item> = (Dispatchers.Default){
                getListFilms(response, genre)
            }

            data.addAll(listHeaderGenre)
            data.addAll(listGenre)
            data.addAll(listHeaderFilm)
            data.addAll(listFilm)

            viewState.setData(data)

        } else{
            viewState.showError(response.toString())
        }


    }

    private fun getHeader(header: String): MutableList<Item>{
        return mutableListOf(
            FeedHeader(header)
        )
    }

    private fun getListFilms(response: Result<FilmData>) : MutableList<Item> {
        val listFilm = mutableListOf<Film>()

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

                listFilm.add(
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

        listFilm.sortBy { it.name }

        return listFilm.toMutableList()
    }

    private fun getListFilms(response: Result<FilmData>, genreFilter: FeedGenre) : MutableList<Item>{
        val listFilm = mutableListOf<Film>()

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

                if (genres!!.contains(genreFilter.title)){
                    listFilm.add(
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

        listFilm.sortBy { it.name }

        return listFilm.toMutableList()
    }

    private fun getListGenreGroup(response: Result<FilmData>): MutableList<Item> {
        val listGenre = mutableListOf<FeedGenre>()

        val getItems = response.asSuccess().value
        val items = getItems.films

        if (items != null) {
            for (item in items.indices) {

                for (i in 0 until items.count()) {
                    val genres = items[i].genres

                    if (genres != null) {
                        for (element in genres) {
                            if (listGenre.none { listGenre.contains(FeedGenre(element)) }) {
                                listGenre.add(FeedGenre(title = element))
                            }
                        }
                    }

                }
            }
        }

        return mutableListOf(FeedGenreGroup(listGenre))
    }

}



