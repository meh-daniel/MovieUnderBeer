package meh.daniel.com.movieunderbeer.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import meh.daniel.com.movieunderbeer.adapter.MovieListAdapter
import meh.daniel.com.movieunderbeer.adapter.base.BrewerysprintAdapter
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.adapter.brewerysprint.FilmBrewerysprint
import meh.daniel.com.movieunderbeer.adapter.brewerysprint.GenreGroupBrewerysprint
import meh.daniel.com.movieunderbeer.adapter.brewerysprint.HeaderBrewerysprint
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieListBinding
import meh.daniel.com.movieunderbeer.entities.films.Film
import meh.daniel.com.movieunderbeer.entities.recyclerfeed.FeedGenre
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieListPresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieListView
import meh.daniel.com.movieunderbeer.ui.base.BaseFragment
import moxy.presenter.InjectPresenter

class MovieListFragment : BaseFragment<FragmentMovieListBinding>(), MovieListView {

    @InjectPresenter
    lateinit var movieListPresenter: MovieListPresenter

    private var adapter: BrewerysprintAdapter = MovieListAdapter(listOf(
        HeaderBrewerysprint(),
        GenreGroupBrewerysprint(::onListGenreClick),
        FilmBrewerysprint(::onListFilmClick)
    ))

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMovieListBinding.inflate(inflater, container, false)

    override fun injectDependency(){
        movieListPresenter.injectDependency()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency()
    }

    override fun init() {
        setupAdapter()
    }

    override fun setData(dataList: MutableList<Item>) {
        with(binding){
            contentFilms.postDelayed({
                adapter.submitList(dataList)
            }, 200)
        }
    }

    override fun onListFilmClick(film: Film)  {
        movieListPresenter.openFilm(film)
    }

    override fun onListGenreClick(genre: FeedGenre)  {
        movieListPresenter.getMovieBySelect(genre)
    }

    private fun setupAdapter() {
        with(binding) {
            contentFilms.layoutManager = GridLayoutManager(context, 4)
            contentFilms.adapter = this@MovieListFragment.adapter
            adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

}




