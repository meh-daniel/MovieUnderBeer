package meh.daniel.com.movieunderbeer.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.adapters.recycler.FingerprintAdapter
import meh.daniel.com.movieunderbeer.adapters.recycler.animations.AddableItemAnimator
import meh.daniel.com.movieunderbeer.adapters.recycler.animations.custom.SimpleCommonAnimator
import meh.daniel.com.movieunderbeer.adapters.recycler.animations.custom.SlideInLeftCommonAnimator
import meh.daniel.com.movieunderbeer.adapters.recycler.animations.custom.SlideInTopCommonAnimator
import meh.daniel.com.movieunderbeer.adapters.recycler.common.Item
import meh.daniel.com.movieunderbeer.adapters.recycler.decorations.FeedHorizontalDividerItemDecoration
import meh.daniel.com.movieunderbeer.adapters.recycler.decorations.GroupVerticalItemDecoration
import meh.daniel.com.movieunderbeer.adapters.recycler.fingerprints.FilmFingerprint
import meh.daniel.com.movieunderbeer.adapters.recycler.fingerprints.TitleFingerprint
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieListBinding
import meh.daniel.com.movieunderbeer.model.entities.helpers.FeedTitle
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieListPresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieListView
import meh.daniel.com.movieunderbeer.ui.base.BaseFragment
import moxy.presenter.InjectPresenter

class MovieListFragment : BaseFragment(), MovieListView {

    private lateinit var binding: FragmentMovieListBinding

    @InjectPresenter
    lateinit var movieListPresenter: MovieListPresenter
    private lateinit var adapter: FingerprintAdapter

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
    private fun getFingerprints() = listOf(
        TitleFingerprint(),
        FilmFingerprint()
    )
    override fun setData(dataList: MutableList<Item>) {
        adapter = FingerprintAdapter(getFingerprints())
        try {
            with(binding.contentFilms) {
                layoutManager = GridLayoutManager(context, 4)
                adapter = this@MovieListFragment.adapter
            }
            adapter.submitList( dataList )
        }catch (e : Exception){
            Log.d("expection:", "${e.toString()} fuck scope1")
        }
    }
}




