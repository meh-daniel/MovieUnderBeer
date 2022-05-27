package meh.daniel.com.movieunderbeer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import meh.daniel.com.movieunderbeer.adapter.MovieListAdapter
import meh.daniel.com.movieunderbeer.adapter.base.BrewerysprintAdapter
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.adapter.brewerysprint.FilmBrewerysprint
import meh.daniel.com.movieunderbeer.adapter.brewerysprint.GenreBrewerysprint
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
    GenreBrewerysprint(::onListGenreClick),
    FilmBrewerysprint(::onListFilmClick)
    ))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency()
        movieListPresenter.start()
    }

    override fun injectDependency(){
        movieListPresenter.injectDependency()
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMovieListBinding.inflate(inflater, container, false)

    override fun setupAdapter() {
        with(binding.contentFilms) {
            layoutManager = GridLayoutManager(context, 4)
            adapter = this@MovieListFragment.adapter
        }
    }

    override fun setData(dataList: MutableList<Item>) {
        binding.contentFilms.postDelayed({
            adapter.submitList(dataList)
        }, 200)
    }

    override fun onListFilmClick(film: Film)  {
        film.id?.let { movieListPresenter.openFilm() }
    }

    override fun onListGenreClick(genre: FeedGenre)  {
        movieListPresenter.getMovieByGenre(genre)
    }


}




