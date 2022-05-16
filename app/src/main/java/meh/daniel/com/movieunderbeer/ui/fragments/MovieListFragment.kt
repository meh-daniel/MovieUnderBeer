package meh.daniel.com.movieunderbeer.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import meh.daniel.com.movieunderbeer.adapters.recycler.FingerprintAdapter
import meh.daniel.com.movieunderbeer.adapters.recycler.fingerprints.FilmFingerprint
import meh.daniel.com.movieunderbeer.adapters.recycler.fingerprints.GenreFingerprint
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieListBinding
import meh.daniel.com.movieunderbeer.di.modules.ApiModule
import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
import meh.daniel.com.movieunderbeer.model.entities.Film
import meh.daniel.com.movieunderbeer.model.entities.FilmData
import moxy.MvpAppCompatFragment
import retrofit2.Response
import java.util.ArrayList

class MovieListFragment : MvpAppCompatFragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var adapter: FingerprintAdapter


//        listOf(
//        Film(id = 1, localizedName = "123123", name = null, year = null, rating = 3.4, imageUrl = null, description = null, genres = null))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        setupRecyclerview()
        return binding.root
    }


    private fun setupRecyclerview() {
        adapter = FingerprintAdapter(getFingerprints())

        with(binding.contentFilms) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MovieListFragment.adapter
        }
        try {
//            adapter.setItems()
        } catch (e: Exception) {
            Log.d("ayush:","${e.toString()} в setupRecyclerview")
        }

    }

    private fun getFingerprints() = listOf(
        FilmFingerprint(),
        GenreFingerprint()
    )

    private fun getApi(): List<Film>{
        lateinit var listFilm: ArrayList<Film>
        lateinit var result: Response<FilmData>
        GlobalScope.launch {
            try {
                val api = ApiModule().api(Constants.API_URL, ApiModule().gson())
                val repo = RepositoryModule().filmRepository(api)
                result = repo.loadFilms()

                if (result.isSuccessful) {
                    val items = result.body()?.films
                    Log.d("ayush:", "count ${items?.count().toString()}")
                    if (items != null) {

                        listFilm = ArrayList(items.count())

                        for (i in 0 until items.count()) {
                            val id = items[i].id?.toInt()
                            val localName = items[i].localizedName
                            val rating = items[i].rating
                            listFilm.add(Film(id = id, localizedName = localName.toString(), name = null, year = null, rating = rating, imageUrl = null, description = null, genres = null))
                        }

                    }
                }
            } catch (e: Exception) {
                Log.d("expection:", "${e.toString()} fuck")
            }
        }
        return listFilm.toList()
    }

}
