package meh.daniel.com.movieunderbeer.presentation.ui.moviedetails

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.forEach
import androidx.core.view.isEmpty
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.databinding.AlertDialogErrorBinding
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieDetailsBinding
import meh.daniel.com.movieunderbeer.domain.entities.films.Film
import meh.daniel.com.movieunderbeer.presentation.navigation.common.IBackButtonListener
import meh.daniel.com.movieunderbeer.presentation.mvp.moviedetails.MovieDetailsPresenter
import meh.daniel.com.movieunderbeer.presentation.mvp.moviedetails.MovieDetailsView
import meh.daniel.com.movieunderbeer.presentation.ui.base.BaseFragment
import moxy.presenter.InjectPresenter

class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>(), MovieDetailsView, IBackButtonListener {

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
        fun newInstance(dataFilm: Int): MovieDetailsFragment {
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

    override fun backPressed() : Boolean = movieDetailsPresenter.backExit()

    override fun loadFilm(film: Film) {
        with(binding.includeContent){
            filmTitleLocalizedName.text = film.localizedName
            when{
                film.name != film.localizedName ->{
                    filmTitleName.text = film.name
                }
            }
            when(film.genres?.size){
                0 ->{
                    textLabelInfo.visibility = View.VISIBLE
                    filmGroupGenre.visibility = View.GONE
                }
                else -> {
                    genresGen(item = film, filmGroupGenre)
                }
            }
            when (film.rating) {
                null ->
                    filmRating.text = resources.getText(R.string.missing)
                else -> {
                    filmRating.text = film.rating.toString()
                }
            }
            when (film.year) {
                null ->
                    filmYear.text = resources.getText(R.string.missing)
                else -> {
                    filmYear.text = film.year.toString()
                }
            }
            when (film.description) {
                null ->
                    filmTextDescription.text = resources.getText(R.string.missing)
                else -> {
                    filmTextDescription.text = film.description.toString()
                }
            }
        }
        with(binding.toolbarLayout){
            title = resources.getText(R.string.toolbar_movie)
        }
        with(binding){
            when {
                film.imageUrl.isNullOrEmpty() -> {
                    imageMovieBackdrop.visibility = View.GONE
                }
                film.imageUrl == "null"-> {
                    imageMovieBackdrop.visibility = View.GONE
                }
                else -> {
                    Glide.with(imageMovieBackdrop)
                        .load(film.imageUrl)
                        .into(imageMovieBackdrop)
                }
            }
        }
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

    private fun genresGen(item: Film, chipGroup: ChipGroup){
        if (chipGroup.isEmpty()){
            for (i in item.genres!!) {
                val chipGenre = Chip(context)
                val chipGenreDrawable = ChipDrawable.createFromAttributes(
                    chipGroup.context,
                    null,
                    0,
                    R.style.chips_theme
                )
                chipGenre.setChipDrawable(chipGenreDrawable)
                chipGenre.text = i
                chipGroup.addView(chipGenre)
            }
            chipGroup.forEach { it.isEnabled = false }
        }
    }

    private fun initListenerActionsFilm(){
        with(binding){
            includeToolbar.toolbarNavigationBackButton.setOnClickListener {
                backPressed()
            }
        }
    }

}