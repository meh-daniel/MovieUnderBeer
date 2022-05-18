//package meh.daniel.com.movieunderbeer.ui.fragments
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.ConcatAdapter
//import androidx.recyclerview.widget.LinearLayoutManager
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import meh.daniel.com.movieunderbeer.R
//import meh.daniel.com.movieunderbeer.adapters.recycler.FingerprintAdapter
//import meh.daniel.com.movieunderbeer.adapters.recycler.animations.AddableItemAnimator
//import meh.daniel.com.movieunderbeer.adapters.recycler.animations.custom.SimpleCommonAnimator
//import meh.daniel.com.movieunderbeer.adapters.recycler.animations.custom.SlideInLeftCommonAnimator
//import meh.daniel.com.movieunderbeer.adapters.recycler.animations.custom.SlideInTopCommonAnimator
//import meh.daniel.com.movieunderbeer.adapters.recycler.common.Item
//import meh.daniel.com.movieunderbeer.adapters.recycler.common.ItemFingerprint
//import meh.daniel.com.movieunderbeer.adapters.recycler.common.helpers.TitleFingerprint
//import meh.daniel.com.movieunderbeer.adapters.recycler.decorations.FeedHorizontalDividerItemDecoration
//import meh.daniel.com.movieunderbeer.adapters.recycler.decorations.GroupVerticalItemDecoration
//import meh.daniel.com.movieunderbeer.adapters.recycler.fingerprints.FilmFingerprint
//import meh.daniel.com.movieunderbeer.app.Constants
//import meh.daniel.com.movieunderbeer.databinding.FragmentMovieListBinding
//import meh.daniel.com.movieunderbeer.di.modules.ApiModule
//import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
//import meh.daniel.com.movieunderbeer.model.entities.films.Film
//import meh.daniel.com.movieunderbeer.model.entities.films.FilmData
//import meh.daniel.com.movieunderbeer.model.entities.helpers.FeedTitle
//import meh.daniel.com.movieunderbeer.mvp.presenters.MovieListPresenter
//import meh.daniel.com.movieunderbeer.mvp.view.MovieListView
//import meh.daniel.com.movieunderbeer.ui.base.BaseFragment
//import moxy.MvpAppCompatFragment
//import moxy.presenter.InjectPresenter
//import moxy.presenter.ProvidePresenter
//import retrofit2.Response
//import java.util.ArrayList
//
//class MovieListFragment : BaseFragment(), MovieListView {
//
//    private lateinit var binding: FragmentMovieListBinding
//
//    private val titlesList: MutableList<Item> by lazy {
//        MutableList(1) { FeedTitle("Та за шо ты меня так") }
//    }
//
//    private val filmsList: MutableList<Item> by lazy {
//        MutableList(1) {Film(id = 1, localizedName = "localName", name = null, year = null, rating = 3.3, imageUrl = null, description = null, genres = null)}
//    }
//
//    private val titleAdapter = FingerprintAdapter(listOf(TitleFingerprint()))
//    private val filmAdapter = FingerprintAdapter(listOf(FilmFingerprint()))
//
//    private val concatAdapter = ConcatAdapter(
//        ConcatAdapter.Config.Builder()
//            .setIsolateViewTypes(false)
//            .build(),
//        titleAdapter,
//        filmAdapter
//    )
//
//
//    @InjectPresenter
//    lateinit var movieListPresenter: MovieListPresenter
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentMovieListBinding.inflate(inflater, container, false)
//        injectDependency()
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        injectDependency()
//        workApi()
//    }
//
//    override fun injectDependency(){
//        movieListPresenter.injectDependency()
//    }
//    private fun workApi(){
//        lateinit var listFilm: List<Item>
//        lateinit var result: Response<FilmData>
//        GlobalScope.launch {
//            try {
//                val api = ApiModule().api(Constants.API_URL, ApiModule().gson())
//                val repo = RepositoryModule().filmRepository(api)
//                result = repo.loadFilms()
//            } catch (e: Exception) {
//                Log.d("expection:", "${e.toString()} fuck")
//            }
//
//            try {
//                if (result.isSuccessful) {
//                    val items = result.body()?.films
//
//                    if (items != null) {
//
//                        for (i in 0 until items.count()) {
//                            val id = items[i].id?.toInt()
//                            Log.d("xxx:", "${id.toString()} fuck")
//                            val localName = items[i].localizedName.toString()
//                            Log.d("xxx:", "${localName.toString()} fuck")
//                            val rating = items[i].rating?.toDouble()
//                            Log.d("xxx:", "${rating.toString()} fuck")
//                            if(i == 0){
//                                listFilm = List(items.count()){
//                                    Film(id = id, localizedName = localName, name = null, year = null, rating = rating, imageUrl = null, description = null, genres = null)
//                                }
//                            }
//                            listFilm = listFilm + listOf<Item>(
//                                Film(id = id, localizedName = localName, name = null, year = null, rating = rating, imageUrl = null, description = null, genres = null)
//                            )
//
//                        }
//                    }
//                }
//            } catch (e: Exception) {
//                Log.d("expection:", "${e.toString()} fuck")
//            }
//            movieListPresenter.start(listFilm)
//        }
//    }
//
//    override fun setupAdapter() {
//        with(binding.contentFilms) {
//            layoutManager = LinearLayoutManager(context)
//            adapter = concatAdapter
//
//            addItemDecoration(FeedHorizontalDividerItemDecoration(70))
//            addItemDecoration(GroupVerticalItemDecoration(R.layout.item_title, 50, 100))
//            addItemDecoration(GroupVerticalItemDecoration(R.layout.item_film, 40, 0))
//
//            itemAnimator = AddableItemAnimator(SimpleCommonAnimator()).also { animator ->
//                animator.addViewTypeAnimation(R.layout.item_title, SlideInTopCommonAnimator())
//                animator.addViewTypeAnimation(R.layout.item_film, SlideInLeftCommonAnimator())
//                animator.addDuration = 500L
//                animator.removeDuration = 500L
//            }
//        }
//    }
//
//    override fun setData(items: List<Item>) {
//        binding.contentFilms.postDelayed({
//            titleAdapter.submitList(titlesList.toList())
//            filmAdapter.submitList(filmsList.toList())
//        }, 300L)
//    }
//}
//
//
//
//
//
//////////////////////////////
//package meh.daniel.com.movieunderbeer.mvp.presenters
//
//import android.util.Log
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import meh.daniel.com.movieunderbeer.adapters.recycler.common.Item
//import meh.daniel.com.movieunderbeer.app.App
//import meh.daniel.com.movieunderbeer.app.Constants
//import meh.daniel.com.movieunderbeer.di.modules.ApiModule
//import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
//import meh.daniel.com.movieunderbeer.model.entities.films.Film
//import meh.daniel.com.movieunderbeer.model.entities.films.FilmData
//import meh.daniel.com.movieunderbeer.mvp.base.BasePresenter
//import meh.daniel.com.movieunderbeer.mvp.view.MovieListView
//import moxy.InjectViewState
//import retrofit2.Response
//
//@InjectViewState
//class MovieListPresenter : BasePresenter<MovieListView>() {
//    override fun injectDependency() {
//        App.instance.appComponent.inject(this)
//    }
//
//
//
////    private var filmsList: MutableList<Item> = MutableList(1){Film(id = 1, localizedName = "localName", name = null, year = null, rating = 3.3, imageUrl = null, description = null, genres = null)}
//
//    fun start(filmsList : List<Item>){
//        GlobalScope.launch {
//            try {
//                viewState.setupAdapter()
//                viewState.setData(filmsList)
//            }catch (e:Exception){
//                Log.d("expection:", "${e.toString()} fuck scope1")
//            }
//        }
//    }
//
//    private fun getApi() : Response<FilmData>{
//        lateinit var response: Response<FilmData>
//        GlobalScope.launch {
//            try {
//                val api = ApiModule().api(Constants.API_URL, ApiModule().gson())
//                val repo = RepositoryModule().filmRepository(api)
//                response = repo.loadFilms()
//            } catch (e: Exception) {
//                Log.d("expection:", "${e.toString()} fuck")
//            }
//        }
//        return response
//    }
//
//    private fun getBodyResponse(response: Response<FilmData>): MutableList<Item>{
//        var listFilm = mutableListOf<Item>()
//        GlobalScope.launch {
//            try {
//                if (response.isSuccessful) {
//                    val items = response.body()?.films
//
//                    if (items != null) {
//                        for (i in 0 until items.count()) {
//                            val id = items[i].id?.toInt()
//                            val localName = items[i].localizedName.toString()
//                            val rating = items[i].rating?.toDouble()
//                            val imageUrl = items[i].imageUrl?.toString()
//                            listFilm.add(i, Film(id = id, localizedName = localName, name = null, year = null, rating = rating, imageUrl = imageUrl, description = null, genres = null))
//                            Log.d("xxx:", "${listFilm[i].toString()} fuck")
//                        }
//                    }
//
//                }
//            } catch (e: Exception) {
//                Log.d("expection:", "${e.toString()} fuck")
//            }
//        }
//        Log.d("expection:", "${listFilm[5].toString()} fuck")
//        return listFilm
//    }
//
//}