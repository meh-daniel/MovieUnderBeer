package meh.daniel.com.movieunderbeer.ui

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import meh.daniel.com.movieunderbeer.adapters.recycler.common.Item
import meh.daniel.com.movieunderbeer.adapters.recycler.common.helpers.TitleFingerprint
import meh.daniel.com.movieunderbeer.adapters.recycler.fingerprints.FilmFingerprint
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.di.modules.ApiModule
import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
import meh.daniel.com.movieunderbeer.model.entities.films.Film
import meh.daniel.com.movieunderbeer.model.entities.films.FilmData
import meh.daniel.com.movieunderbeer.model.entities.helpers.FeedTitle
import retrofit2.Response

//class cherno {
//
//private var list1 : List<Item> = List(1) {
//    FeedTitle(title = "ТА КАКОГО ХРЕНА ТО");
//    Film(id = 1, localizedName = "123123", name = null, year = null, rating = 3.4, imageUrl = null, description = null, genres = null)
//}
//
//fun getRandom() = List(4){
//    when (it){
//        0 -> FeedTitle(title = "ТА КАКОГО ХРЕНА ТО")
//        1 -> Film(id = 1, localizedName = "first", name = null, year = null, rating = 3.4, imageUrl = null, description = null, genres = null)
//        2 -> Film(id = 1, localizedName = "second", name = null, year = null, rating = 3.4, imageUrl = null, description = null, genres = null)
//        3 -> Film(id = 1, localizedName = "thist", name = null, year = null, rating = 3.4, imageUrl = null, description = null, genres = null)
//        else -> {Film(id = 2, localizedName = "второй", name = null, year = null, rating = 3.6
//
//            , imageUrl = null, description = null, genres = null)}
//    }
//}
//
//    private fun getApi(): List<Item>{
//        lateinit var listFilm: List<Item>
//        lateinit var result: Response<FilmData>
//        GlobalScope.launch {
//            try {
//                val api = ApiModule().api(Constants.API_URL, ApiModule().gson())
//                val repo = RepositoryModule().filmRepository(api)
//                result = repo.loadFilms()
//            } catch (e: Exception) {
//                Log.d("expection:", "${e.toString()} fuck")
//            }
//        }
//
//        if (result.isSuccessful) {
//            val items = result.body()?.films
//
//            if (items != null) {
//
//                for (i in 0..1) {
//                    val id = items[i].id?.toInt()
//                    val localName = items[i].localizedName.toString()
//                    val rating = items[i].rating?.toDouble()
//                    listFilm = List(1){
//                        Film(id = id, localizedName = localName, name = null, year = null, rating = rating, imageUrl = null, description = null, genres = null)
//                    }
//                }
//            }
//        }
//
//
//        return listFilm
//    }
//}