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
import meh.daniel.com.movieunderbeer.adapters.recycler.common.Item
import meh.daniel.com.movieunderbeer.adapters.recycler.common.ItemFingerprint
import meh.daniel.com.movieunderbeer.adapters.recycler.common.helpers.TitleFingerprint
import meh.daniel.com.movieunderbeer.adapters.recycler.fingerprints.FilmFingerprint
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieListBinding
import meh.daniel.com.movieunderbeer.di.modules.ApiModule
import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
import meh.daniel.com.movieunderbeer.model.entities.films.Film
import meh.daniel.com.movieunderbeer.model.entities.films.FilmData
import meh.daniel.com.movieunderbeer.model.entities.helpers.FeedTitle
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieListPresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieListView
import meh.daniel.com.movieunderbeer.ui.base.BaseFragment
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import retrofit2.Response
import java.util.ArrayList

class MovieListFragment : BaseFragment(), MovieListView {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var adapter: FingerprintAdapter


    @InjectPresenter
    lateinit var movieListPresenter: MovieListPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        injectDependency()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency()
        workApi()
    }


    private fun workApi(){
        lateinit var listFilm: List<Item>
        lateinit var result: Response<FilmData>
        GlobalScope.launch {
            try {
                val api = ApiModule().api(Constants.API_URL, ApiModule().gson())
                val repo = RepositoryModule().filmRepository(api)
                result = repo.loadFilms()
            } catch (e: Exception) {
                Log.d("expection:", "${e.toString()} fuck")
            }

            try {
                if (result.isSuccessful) {
                    val items = result.body()?.films

                    if (items != null) {

                        for (i in 0 until items.count()) {
                            val id = items[i].id?.toInt()
                            Log.d("xxx:", "${id.toString()} fuck")
                            val localName = items[i].localizedName.toString()
                            Log.d("xxx:", "${localName.toString()} fuck")
                            val rating = items[i].rating?.toDouble()
                            Log.d("xxx:", "${rating.toString()} fuck")
                            if(i == 0){
                                listFilm = List(i){
                                    Film(id = id, localizedName = localName, name = null, year = null, rating = rating, imageUrl = null, description = null, genres = null)
                                }
                            }
                            listFilm = listFilm + listOf<Item>(
                                Film(id = id, localizedName = localName, name = null, year = null, rating = rating, imageUrl = null, description = null, genres = null)
                            )

                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("expection:", "${e.toString()} fuck")
            }
            movieListPresenter.start(listFilm)
        }
    }


    private fun getFingerprints() = listOf(
        TitleFingerprint(),
        FilmFingerprint()
    )
    override fun injectDependency(){
            movieListPresenter.injectDependency()
    }


    override fun setupAdapter() {
        adapter = FingerprintAdapter(getFingerprints())
        with(binding.contentFilms) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MovieListFragment.adapter
        }
    }

    override fun setData(items: List<Item>) {
        adapter.setItems(items)
    }


}
