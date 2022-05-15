package meh.daniel.com.movieunderbeer.ui.fragments

import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieListBinding
import meh.daniel.com.movieunderbeer.di.modules.ApiModule
import meh.daniel.com.movieunderbeer.di.modules.RepositoryModule
import moxy.MvpAppCompatFragment

class MovieListFragment : MvpAppCompatFragment() {

    private lateinit var binding: FragmentMovieListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)

//        GlobalScope.launch {
//            val api = ApiModule().api(Constants.API_URL, ApiModule().gson())
//            val repo = RepositoryModule().filmRepository(api)
//            val result = repo.loadFilms()
//            Log.d("ayush:", result.body().toString())
//        }

        return binding.root
    }
}
