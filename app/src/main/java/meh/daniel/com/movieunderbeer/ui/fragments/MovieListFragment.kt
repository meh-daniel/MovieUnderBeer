package meh.daniel.com.movieunderbeer.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import meh.daniel.com.movieunderbeer.adapter.ItemTypes
import meh.daniel.com.movieunderbeer.adapter.MovieListAdapter
import meh.daniel.com.movieunderbeer.adapter.ViewHoldersManagerImpl
import meh.daniel.com.movieunderbeer.adapter.base.BaseListAdapter
import meh.daniel.com.movieunderbeer.adapter.common.AdapterClickListenerById
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.adapter.viewHolders.FilmViewHolder
import meh.daniel.com.movieunderbeer.adapter.viewHolders.GenreViewHolder
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieListBinding
import meh.daniel.com.movieunderbeer.di.modules.AdapterModule
import meh.daniel.com.movieunderbeer.di.modules.ApiModule
import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
import meh.daniel.com.movieunderbeer.entities.films.Film
import meh.daniel.com.movieunderbeer.entities.films.FilmData
import meh.daniel.com.movieunderbeer.entities.helpers.FeedGenre
import meh.daniel.com.movieunderbeer.entities.helpers.FeedHeader
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieListPresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieListView
import meh.daniel.com.movieunderbeer.ui.base.BaseFragment
import moxy.presenter.InjectPresenter
import retrofit2.Response
import ru.alexmaryin.recycleronvisitor.ui.adapter.ViewHoldersManager
import ru.alexmaryin.recycleronvisitor.ui.viewHolders.HeaderViewHolder
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class MovieListFragment : BaseFragment(), MovieListView {

    private lateinit var binding: FragmentMovieListBinding

    @InjectPresenter
    lateinit var movieListPresenter: MovieListPresenter

    override fun injectDependency() {
        movieListPresenter.injectDependency()
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
        Log.d("expection:", "start1")
    }

    override fun setupAdapter(dataList : MutableList<Item>) {
        val itemsAdapter : BaseListAdapter = MovieListAdapter(AdapterClickListenerById {}, AdapterModule().provideAdaptersManager())
        itemsAdapter.submitList(dataList.toList())
        try {
            with(binding.contentFilms) {
                layoutManager = GridLayoutManager(context, 4)
                adapter = itemsAdapter
            }
        }catch (e : Exception){
            Log.d("expection:", "${e.toString()} fuck scope1")
        }
    }
}




