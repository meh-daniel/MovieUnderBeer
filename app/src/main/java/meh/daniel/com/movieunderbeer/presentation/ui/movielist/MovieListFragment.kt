package meh.daniel.com.movieunderbeer.presentation.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.databinding.AlertDialogErrorBinding
import meh.daniel.com.movieunderbeer.presentation.ui.movielist.adapter.MovieListAdapter
import meh.daniel.com.movieunderbeer.presentation.adapter.BrewerysprintAdapter
import meh.daniel.com.movieunderbeer.presentation.adapter.common.Item
import meh.daniel.com.movieunderbeer.presentation.adapter.brewerysprint.FilmBrewerysprint
import meh.daniel.com.movieunderbeer.presentation.adapter.brewerysprint.GenreGroupBrewerysprint
import meh.daniel.com.movieunderbeer.presentation.adapter.brewerysprint.HeaderBrewerysprint
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieListBinding
import meh.daniel.com.movieunderbeer.domain.entities.films.Film
import meh.daniel.com.movieunderbeer.domain.entities.recyclerfeed.FeedGenre
import meh.daniel.com.movieunderbeer.presentation.mvp.movielist.MovieListPresenter
import meh.daniel.com.movieunderbeer.presentation.mvp.movielist.MovieListView
import meh.daniel.com.movieunderbeer.presentation.ui.base.BaseFragment
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

    override fun injectDependency() {
        movieListPresenter.injectDependency()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency()
    }

    override fun init() {
        initToolBar()
        setupAdapter()
    }

    override fun setData(dataList: MutableList<Item>) {
        with(binding){
            contentFilms.postDelayed({
                adapter.submitList(dataList)
            }, 200)
        }
    }

    override fun onListFilmClick(film: Film) {
        movieListPresenter.openFilm(film)
    }

    override fun onListGenreClick(genre: FeedGenre) {
        movieListPresenter.getMovieBySelect(genre)
    }

    override fun showError(message: String) {
        val dialogBinding: AlertDialogErrorBinding = AlertDialogErrorBinding.inflate(layoutInflater)
        val dialog: AlertDialog = AlertDialog.Builder(this.requireContext(), R.style.dialog_error_theme)
            .setView(dialogBinding.root)
            .create()
        dialog.show()
        dialogBinding.errorBodyText.text = message
        dialogBinding.errorButtonClose.setOnClickListener { dialog.dismiss() }
    }

    private fun initToolBar() {
        with(binding){
            includeToolbar.toolbar.title = resources.getText(R.string.movies)
            includeToolbar.toolbarNavigationBackButton.visibility = View.GONE
        }
    }

    private fun setupAdapter() {
        with(binding) {
            contentFilms.layoutManager = GridLayoutManager(context, 4)
            contentFilms.adapter = this@MovieListFragment.adapter
            adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            contentFilms.isMotionEventSplittingEnabled = false
        }
    }

}




