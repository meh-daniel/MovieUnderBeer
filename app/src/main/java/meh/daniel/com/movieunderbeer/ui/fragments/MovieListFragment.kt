package meh.daniel.com.movieunderbeer.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import meh.daniel.com.movieunderbeer.adapter.FingerprintAdapter
import meh.daniel.com.movieunderbeer.adapter.FilmFingerprint
import meh.daniel.com.movieunderbeer.adapter.GenreFingerprint
import meh.daniel.com.movieunderbeer.adapter.HeaderFingerprint
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieListBinding
import meh.daniel.com.movieunderbeer.entities.films.Film
import meh.daniel.com.movieunderbeer.entities.recyclerfeed.FeedGenre
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieListPresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieListView
import meh.daniel.com.movieunderbeer.ui.base.BaseFragment
import moxy.presenter.InjectPresenter

class MovieListFragment : BaseFragment(), MovieListView {

    @InjectPresenter
    lateinit var movieListPresenter: MovieListPresenter

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var adapter: FingerprintAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        movieListPresenter.start()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun injectDependency(){
        movieListPresenter.injectDependency()
    }

    override fun setupAdapter() {
        try {
            adapter = FingerprintAdapter(listOf(
                HeaderFingerprint(),
                GenreFingerprint(::onListGenreClick),
                FilmFingerprint(::onListFilmClick)
            ))
            with(binding.contentFilms) {
                layoutManager = GridLayoutManager(context, 4)
                adapter = this@MovieListFragment.adapter
            }
        }catch (e : Exception){
            Log.d("expection:", "${e.toString()} fuck scope1")
        }
    }

    override fun setData(genresListData: MutableList<Item>, filmsListData: MutableList<Item>) {
        binding.contentFilms.postDelayed({
            adapter.submitList(genresListData.toList() + filmsListData.toList())
        }, 200)
    }

    private fun onListFilmClick(film: Film)  {
        Log.d("xxx:", "${film.toString()} fuck scope1")
    }

    private fun onListGenreClick(genre: FeedGenre)  {
        Log.d("xxx:", "${genre.title} fuck scope1")
    }

}




