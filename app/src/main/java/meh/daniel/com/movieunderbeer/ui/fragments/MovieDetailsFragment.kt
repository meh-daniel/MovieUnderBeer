package meh.daniel.com.movieunderbeer.ui.fragments

import android.os.Bundle
import android.view.*
import com.bumptech.glide.Glide
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieDetailsBinding
import meh.daniel.com.movieunderbeer.entities.films.Film
import meh.daniel.com.movieunderbeer.mvp.navigation.BackButtonListener
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieDetailsPresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieDetailsView
import meh.daniel.com.movieunderbeer.ui.base.BaseFragment
import moxy.presenter.InjectPresenter


class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>(), MovieDetailsView, BackButtonListener {

    @InjectPresenter
    lateinit var movieDetailsPresenter: MovieDetailsPresenter

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMovieDetailsBinding.inflate(inflater, container, false)

    override fun injectDependency() {
        movieDetailsPresenter.injectDependency()
    }

    companion object{
        private const val ARGS_NAME = "dataFilm"
        fun newInstance(dataFilm: Int): MovieDetailsFragment{
            return MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARGS_NAME, dataFilm)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency()
        arguments?.getInt(ARGS_NAME)?.let { movieDetailsPresenter.start(it) }
    }


    override fun init() {
        initListenerActionsFilm()
    }

    override fun loadFilm(film: Film) {
        with(binding){
            includeToolbar.toolbar.title = film.name

            Glide.with(imageMovieBackdrop)
                .load(film.imageUrl)
                .into(imageMovieBackdrop)

            Glide.with(binding.includeContent.filmPoster)
                .load(film.imageUrl)
                .into(binding.includeContent.filmPoster)

            includeContent.filmTitleLocalizedName.text = film.localizedName
            includeContent.filmTitleName.text = film.name
            includeContent.textOverview.text = film.description
            includeContent.filmYear.text = film.year.toString()
            includeContent.filmRating.text = film.rating.toString()
        }
    }


    private fun initListenerActionsFilm(){
        with(binding){
            includeToolbar.toolbarNavigationBackButton.setOnClickListener {
                backPressed()
            }
        }
    }

    override fun backPressed() : Boolean = movieDetailsPresenter.backExit()


}