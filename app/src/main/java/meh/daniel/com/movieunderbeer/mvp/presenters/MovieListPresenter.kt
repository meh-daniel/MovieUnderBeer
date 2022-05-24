package meh.daniel.com.movieunderbeer.mvp.presenters

import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.app.App
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.di.modules.ApiModule
import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
import meh.daniel.com.movieunderbeer.entities.films.Film
import meh.daniel.com.movieunderbeer.entities.films.FilmData
import meh.daniel.com.movieunderbeer.entities.helpers.FeedHeader
import meh.daniel.com.movieunderbeer.mvp.base.BasePresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieListView
import moxy.InjectViewState
import retrofit2.Response
import java.text.RuleBasedCollator

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
            var dataItems = mutableListOf<Item>()

            GlobalScope.launch {

                try {
                    dataItems.add(
                        FeedHeader("фильмы")
                    )

                    dataItems.add(
                        FeedHeader("фильмы")
                    )
                    if (response.isSuccessful) {
                        val items = response.body()?.films

                        if (items != null) {
                            for (i in 0 until items.count()) {
                                var id = items[i].id?.toInt()
                                var localName = items[i].localizedName
                                var name = items[i].name
                                var rating = items[i].rating
                                var imageUrl = if(items[i].imageUrl != null){
                                    "https://st.kp.yandex.net/images/film_iphone/" + Uri.parse((items[i].imageUrl)).lastPathSegment.toString()
                                }else{
                                    null
                                }
                                dataItems.add(Film(id = id, localizedName = localName, name = name,
                                    year = null, rating = rating, imageUrl =  imageUrl, description = null, genres = null)
                                )
                                Log.d("xxnx:", "film item ${dataItems[i].javaClass}")
                            }
                        }
                    }
                } catch (e: Exception) {
                    Log.d("expection:", "${e.toString()} fuck")
                }

            }
            viewState.setupAdapter(dataItems)
        }
    }

}