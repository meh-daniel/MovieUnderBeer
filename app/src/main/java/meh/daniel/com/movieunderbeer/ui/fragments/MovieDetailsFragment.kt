package meh.daniel.com.movieunderbeer.ui.fragments

import android.os.Bundle
 import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieListBinding
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieDetailsPresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieDetailsView
import meh.daniel.com.movieunderbeer.ui.base.BaseFragment
import moxy.presenter.InjectPresenter

class MovieDetailsFragment : BaseFragment<FragmentMovieListBinding>(), MovieDetailsView {

    @InjectPresenter
    lateinit var movieDetailsPresenter: MovieDetailsPresenter

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMovieListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency()
    }

    override fun injectDependency() {
        movieDetailsPresenter.injectDependency()
    }

    companion object{
        private const val ARGS_NAME = "id"
        fun newInstance(id: Int): MovieDetailsFragment{
            val args = Bundle()
            val fragment = MovieDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }



}