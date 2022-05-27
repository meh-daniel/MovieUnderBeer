package meh.daniel.com.movieunderbeer.mvp.presenters

import android.net.Uri
import kotlinx.coroutines.*
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.app.App
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.di.modules.ApiModule
import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
import meh.daniel.com.movieunderbeer.entities.films.Film
import meh.daniel.com.movieunderbeer.entities.films.FilmData
import meh.daniel.com.movieunderbeer.entities.recyclerfeed.FeedGenre
import meh.daniel.com.movieunderbeer.entities.recyclerfeed.FeedHeader
import meh.daniel.com.movieunderbeer.mvp.base.BasePresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieListView
import moxy.InjectViewState
import retrofit2.Response

@InjectViewState
class MovieListPresenter : BasePresenter<MovieListView>() {
    override fun injectDependency() {
        App.instance.appComponent.inject(this)
    }
    fun start() : Unit = runBlocking {

        val api = ApiModule().api(Constants.API_URL, ApiModule().gson())
        val repo = RepositoryModule().filmRepository(api)
        val response: Response<FilmData> = (Dispatchers.Default){
            repo.loadFilms()
        }

        viewState.setupAdapter()

        val listGenre: MutableList<Item> = (Dispatchers.Default){
            getListGenre(response)
        }
        val listFilm: MutableList<Item> = (Dispatchers.Default){
            getListFilms(response)
        }

        listGenre.addAll(listFilm)

        viewState.setData(listGenre)

    }

    fun openFilm(){
        router.navigateTo(screens.openFilm())
    }

    private fun getListFilms(response: Response<FilmData>) : MutableList<Item>{
        val listFilm = mutableListOf<Item>(
            FeedHeader("Фильмы")
        )

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
                        "https://st.kp.yandex.net/images/film_iphone/" + Uri.parse((items[i].imageUrl)).lastPathSegment.toString()
                    }else{
                        null
                    }
                    val description = items[i].description
                    val genres = items[i].genres

                    listFilm.add(Film(id = id, localizedName = localName, name = name,
                        year = year, rating = rating, imageUrl =  imageUrl, description = description, genres = genres))
                }
            }
        }

        return listFilm
    }

    private fun getListFilms(response: Response<FilmData>, genreFilter: FeedGenre) : MutableList<Item>{
        val listFilm = mutableListOf<Item>(
            FeedHeader("Фильмы")
        )

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
                        "https://st.kp.yandex.net/images/film_iphone/" + Uri.parse((items[i].imageUrl)).lastPathSegment.toString()
                    }else{
                        null
                    }
                    val description = items[i].description
                    val genres = items[i].genres

                    if (genres!!.contains(genreFilter.title)){
                        listFilm.add(Film(id = id, localizedName = localName, name = name,
                            year = year, rating = rating, imageUrl =  imageUrl, description = description, genres = genres))
                    }

                }
            }
        }

        return listFilm
    }

    private fun getListGenre(response: Response<FilmData>) : MutableList<Item>{
        val listGenre = mutableListOf<Item>(
            FeedHeader("Жанры")
        )

        if (response.isSuccessful) {
            val items = response.body()?.films

            if (items != null) {
                for (i in 0 until items.count()) {
                    val genres = items[i].genres

                    if (genres != null) {
                        for (element in genres){
                            if (listGenre.none{ listGenre.contains(FeedGenre(element)) }) {
                                listGenre.add(FeedGenre(title = element))
                            }
                        }
                    }

                }
            }
        }

        return listGenre
    }

}



