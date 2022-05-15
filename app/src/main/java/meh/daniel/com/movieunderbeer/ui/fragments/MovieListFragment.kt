package meh.daniel.com.movieunderbeer.ui.fragments

import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import meh.daniel.com.movieunderbeer.adapter.FingerprintAdapter
import meh.daniel.com.movieunderbeer.adapter.fingerprints.FilmFingerprint
import meh.daniel.com.movieunderbeer.adapter.fingerprints.GenreFingerprint
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieListBinding
import meh.daniel.com.movieunderbeer.databinding.ItemFilmBinding
import meh.daniel.com.movieunderbeer.di.modules.ApiModule
import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
import meh.daniel.com.movieunderbeer.model.entities.Film
import meh.daniel.com.movieunderbeer.model.entities.FilmX
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieListPresenter
import moxy.MvpAppCompatFragment
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Response
import kotlinx.serialization.*

class MovieListFragment : MvpAppCompatFragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var adapter: FingerprintAdapter
    var genre = listOf<String>("1", "2")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)

        getApi()
        setupRecyclerview()
        return binding.root
    }

    private fun setupRecyclerview() {
        adapter = FingerprintAdapter(getFingerprints())

        with(binding.contentFilms) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MovieListFragment.adapter
        }
        adapter.setItems(getFeed())
    }

    private fun getFeed() = List(10){
        FilmX(1, "name", "", 2010, 3.4, "23123123", "1231231", genre)
    }

    private fun getFingerprints() = listOf(
        FilmFingerprint(),
        GenreFingerprint()
    )

    private fun getApi(){
        GlobalScope.launch {
            val api = ApiModule().api(Constants.API_URL, ApiModule().gson())
            val repo = RepositoryModule().filmRepository(api)
            val result = repo.loadFilms()
            Log.d("ayush:", result.body().toString())
        }
    }

}
