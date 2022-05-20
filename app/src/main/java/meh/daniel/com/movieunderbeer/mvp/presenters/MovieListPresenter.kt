package meh.daniel.com.movieunderbeer.mvp.presenters

import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import meh.daniel.com.movieunderbeer.adapters.recycler.base.BaseListItem
import meh.daniel.com.movieunderbeer.app.App
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.di.modules.ApiModule
import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
import meh.daniel.com.movieunderbeer.model.entities.films.Film
import meh.daniel.com.movieunderbeer.model.entities.films.FilmData
import meh.daniel.com.movieunderbeer.model.entities.helpers.FeedGenre
import meh.daniel.com.movieunderbeer.model.entities.helpers.FeedTitle
import meh.daniel.com.movieunderbeer.mvp.base.BasePresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieListView
import moxy.InjectViewState
import retrofit2.Response

@InjectViewState
class MovieListPresenter : BasePresenter<MovieListView>() {
    override fun injectDependency() {
        App.instance.appComponent.inject(this)
    }

    fun start(){
        GlobalScope.launch {
            try {
                viewState.setupAdapter()
            }catch (e:Exception){
                Log.d("expection:", "${e.toString()} fuck scope1")
            }
            try {

                GlobalScope.launch(Dispatchers.Main) {
                    val api = ApiModule().api(Constants.API_URL, ApiModule().gson())
                    val repo = RepositoryModule().filmRepository(api)
                    var response: Response<FilmData> = repo.loadFilms()
                    Log.d("expection:", "${response.body().toString()} fuck scope1")



                    var dataItems = mutableListOf<BaseListItem>()

                    dataItems.add(FeedTitle("Жанры"))
                    dataItems.add(FeedGenre("Комедия"))
                    dataItems.add(FeedGenre("Трагедия"))
                    dataItems.add(FeedGenre("Комедия"))
                    dataItems.add(FeedGenre("Трагедия"))
                    dataItems.add(FeedGenre("Комедия"))
                    dataItems.add(FeedGenre("Трагедия"))
                    dataItems.add(FeedGenre("Комедия"))
                    dataItems.add(FeedGenre("Трагедия"))
                    dataItems.add(FeedTitle("Фильмы"))
                    GlobalScope.launch {
                        try {
                            if (response.isSuccessful) {
                                val items = response.body()?.films

                                if (items != null) {
                                    for (i in 0 until items.count()) {
                                        var id = items[i].id?.toInt()
                                        var localName = items[i].localizedName
                                        var rating = items[i].rating?.toDouble()
                                        var imageUrl = if(items[i].imageUrl != null){
                                            "https://st.kp.yandex.net/images/film_iphone/" + Uri.parse((items[i].imageUrl)).lastPathSegment.toString()
                                        }else{
                                            null
                                        }
                                        dataItems.add(Film(id = id, localizedName = localName, name = null,
                                            year = null, rating = rating, imageUrl =  imageUrl, description = null, genres = null))
                                        Log.d("xxx:", "${dataItems[i].toString()} fuck")
                                    }
                                }
                                viewState.setData(dataItems)
                            }
                        } catch (e: Exception) {
                            Log.d("expection:", "${e.toString()} fuck")
                        }
                    }
                }
            }catch (e:Exception){
                Log.d("expection:", "${e.toString()} fuck scope2")
            }
        }
    }

}