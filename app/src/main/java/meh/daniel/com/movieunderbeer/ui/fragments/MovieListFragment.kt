package meh.daniel.com.movieunderbeer.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import meh.daniel.com.movieunderbeer.adapters.recycler.base.BaseListItem
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieListBinding
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieListPresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieListView
import meh.daniel.com.movieunderbeer.ui.base.BaseFragment
import moxy.presenter.InjectPresenter

class MovieListFragment : BaseFragment(), MovieListView {

    private lateinit var binding: FragmentMovieListBinding

    private var adapterList = meh.daniel.com.movieunderbeer.adapters.recycler.ListAdapter()

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
        movieListPresenter.start()
    }
    override fun injectDependency(){
        movieListPresenter.injectDependency()
    }

    override fun setupAdapter() {

        try {
            with(binding.contentFilms) {
                layoutManager = GridLayoutManager(context, 4)
                adapter = adapterList
            }

        }catch (e : Exception){
            Log.d("expection:", "${e.toString()} fuck scope1")
        }
    }

    override fun setData(dataList: MutableList<BaseListItem>) {
        adapterList.setItems(dataList.toList())
    }
}




