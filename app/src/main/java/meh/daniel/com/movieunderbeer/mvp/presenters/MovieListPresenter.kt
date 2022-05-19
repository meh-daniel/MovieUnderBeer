package meh.daniel.com.movieunderbeer.mvp.presenters

import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import meh.daniel.com.movieunderbeer.adapter.MovieListAdapter
import meh.daniel.com.movieunderbeer.adapter.base.BrewerysprintAdapter
import meh.daniel.com.movieunderbeer.adapter.brewerysprint.FilmBrewerysprint
import meh.daniel.com.movieunderbeer.adapter.brewerysprint.GenreBrewerysprint
import meh.daniel.com.movieunderbeer.adapter.brewerysprint.HeaderBrewerysprint
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
import okhttp3.ResponseBody
import okhttp3.internal.toImmutableList
import retrofit2.Response

@InjectViewState
class MovieListPresenter : BasePresenter<MovieListView>() {
    override fun injectDependency() {
        App.instance.appComponent.inject(this)
    }
    fun start(){
        GlobalScope.launch(Dispatchers.Main) {

            val api = ApiModule().api(Constants.API_URL, ApiModule().gson())
            val repo = RepositoryModule().filmRepository(api)
            var response: Response<FilmData> = repo.loadFilms()

            viewState.setupAdapter()

            var listGenreGet: MutableList<Item> = getListGenre(response)
            var listFilmGet: MutableList<Item> = getListFilms(response)
            listGenreGet.addAll(listFilmGet)

            viewState.setData(listGenreGet)
        }
    }


    private fun getListFilms(response: Response<FilmData>) : MutableList<Item>{
        var listFilm = mutableListOf<Item>(
            FeedHeader("Фильмы")
        )
        GlobalScope.launch {
            if (response.isSuccessful) {
                val items = response.body()?.films

                if (items != null) {
                    for (i in 0 until items.count()) {
                        var id = items[i].id?.toInt()
                        var localName = items[i].localizedName
                        var name = items[i].name
                        var year = items[i].year
                        var rating = items[i].rating?.toDouble()
                        var imageUrl = if(items[i].imageUrl != null){
                            "https://st.kp.yandex.net/images/film_iphone/" + Uri.parse((items[i].imageUrl)).lastPathSegment.toString()
                        }else{
                            null
                        }

                        var description = items[i].description
                        var genres = items[i].genres
                        listFilm.add(Film(id = id, localizedName = localName, name = name,
                            year = year, rating = rating, imageUrl =  imageUrl, description = description, genres = genres))
                    }
                }
            }
        }
        return listFilm
    }

    private fun getListFilms(response: Response<FilmData>, genreFilter: FeedGenre) : MutableList<Item>{
        var listFilm = mutableListOf<Item>(
            FeedHeader("Фильмы")
        )
        GlobalScope.launch {
            if (response.isSuccessful) {
                val items = response.body()?.films

                if (items != null) {
                    for (i in 0 until items.count()) {
                        var id = items[i].id?.toInt()
                        var localName = items[i].localizedName
                        var name = items[i].name
                        var year = items[i].year
                        var rating = items[i].rating?.toDouble()
                        var imageUrl = if(items[i].imageUrl != null){
                            "https://st.kp.yandex.net/images/film_iphone/" + Uri.parse((items[i].imageUrl)).lastPathSegment.toString()
                        }else{
                            null
                        }
                        var description = items[i].description
                        var genres = items[i].genres

                        if (genres!!.contains(genreFilter.title)){
                            listFilm.add(Film(id = id, localizedName = localName, name = name,
                                year = year, rating = rating, imageUrl =  imageUrl, description = description, genres = genres))
                        }
                    }
                }
            }
        }
        return listFilm
    }

    private fun getListGenre(response: Response<FilmData>) : MutableList<Item>{
        var listGenre = mutableListOf<Item>(
            FeedHeader("Жанры")
        )
        GlobalScope.launch {
            if (response.isSuccessful) {
                val items = response.body()?.films
                Log.d("xxx:", "${items.toString()} items")
                if (items != null) {
                    Log.d("xxx:", "${items.count().toString()} items")
                    for (i in 0 until items.count()) {
                        var genres = items[i].genres

                        if (genres != null) {
                            for (i in 0 until genres.size){
                                var title = genres.get(i)
                                if (listGenre.none{listGenre.contains(FeedGenre(title))}) listGenre.add(FeedGenre(title = title))
                                Log.d("xxx:", "${listGenre[i].toString()}")
                            }
                        }
                    }
                }
            }
        }
        return listGenre
    }

}