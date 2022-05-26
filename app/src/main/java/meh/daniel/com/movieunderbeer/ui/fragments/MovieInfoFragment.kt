package meh.daniel.com.movieunderbeer.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieInfoBinding
import meh.daniel.com.movieunderbeer.entities.films.Film
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieInfoPresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieInfoView
import meh.daniel.com.movieunderbeer.ui.base.BaseFragment
import moxy.presenter.InjectPresenter

class MovieInfoFragment : BaseFragment(), MovieInfoView {

    @InjectPresenter
    lateinit var movieInfoPresenter: MovieInfoPresenter

    private lateinit var binding: FragmentMovieInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency()

        arguments?.let {
            Log.d("xxx", id.toString())
        }

    }

    override fun injectDependency() {
        movieInfoPresenter.injectDependency()
    }

    companion object{
        private const val ARGS_NAME = "id"
        fun newInstance(id: Int): MovieInfoFragment{
            val args = Bundle()
            val fragment = MovieInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

}