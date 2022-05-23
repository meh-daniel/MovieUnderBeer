package meh.daniel.com.movieunderbeer.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import meh.daniel.com.movieunderbeer.adapter.ItemTypes
import meh.daniel.com.movieunderbeer.adapter.MovieListAdapter
import meh.daniel.com.movieunderbeer.adapter.ViewHoldersManagerImpl
import meh.daniel.com.movieunderbeer.adapter.base.BaseListAdapter
import meh.daniel.com.movieunderbeer.adapter.common.AdapterClickListenerById
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.adapter.viewHolders.FilmViewHolder
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieListBinding
import meh.daniel.com.movieunderbeer.entities.films.Film
import meh.daniel.com.movieunderbeer.entities.helpers.FeedHeader
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieListPresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieListView
import meh.daniel.com.movieunderbeer.ui.base.BaseFragment
import moxy.presenter.InjectPresenter
import ru.alexmaryin.recycleronvisitor.ui.adapter.ViewHoldersManager
import ru.alexmaryin.recycleronvisitor.ui.viewHolders.HeaderViewHolder
import javax.inject.Inject

class MovieListFragment : BaseFragment(), MovieListView {

    private lateinit var binding: FragmentMovieListBinding

    @InjectPresenter
    lateinit var movieListPresenter: MovieListPresenter
    @Inject
    lateinit var viewHoldersManager: ViewHoldersManager

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
//        movieListPresenter.start()
        var viewHolderManager: ViewHoldersManager = ViewHoldersManagerImpl().apply {
            registerViewHolder(ItemTypes.HEADER, HeaderViewHolder())
            registerViewHolder(ItemTypes.CARD, FilmViewHolder())
        }
        val itemsAdapter : BaseListAdapter = MovieListAdapter(AdapterClickListenerById {}, viewHolderManager)

        itemsAdapter.submitList(mutableListOf<Item>(
            FeedHeader("Фильмы"),
            Film(id = 1, localizedName = "localName", name = null,
                year = null, rating = 3.3, imageUrl =  null, description = null, genres = null),
            Film(id = 1, localizedName = "localName", name = null,
                year = null, rating = 3.3, imageUrl =  null, description = null, genres = null),
            FeedHeader("Фильмы"),
            Film(id = 1, localizedName = "localName", name = null,
                year = null, rating = 3.3, imageUrl =  null, description = null, genres = null),
            FeedHeader("Фильмы"),
            Film(id = 1, localizedName = "localName", name = null,
                year = null, rating = 3.3, imageUrl =  null, description = null, genres = null)
        ))
        try {
            with(binding.contentFilms) {
                layoutManager = GridLayoutManager(context, 4)
                adapter = itemsAdapter
            }
        }catch (e : Exception){
            Log.d("expection:", "${e.toString()} fuck scope1")
        }

    }

    override fun setupAdapter(dataList : MutableList<Item>) {
        var viewHolderManager: ViewHoldersManager = ViewHoldersManagerImpl().apply {
            registerViewHolder(ItemTypes.HEADER, HeaderViewHolder())
            registerViewHolder(ItemTypes.CARD, FilmViewHolder())
        }
        val itemsAdapter = MovieListAdapter(AdapterClickListenerById {}, viewHolderManager)
        itemsAdapter.submitList(dataList)
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




