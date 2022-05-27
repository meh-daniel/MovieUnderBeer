package meh.daniel.com.movieunderbeer.ui.fragments

import android.os.Bundle
 import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieInfoBinding
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
//        var film = Film(id = 42664, localizedName = "Бойцовский клуб", name = "Fight Club",
//            year = 1999, rating = 8.656, imageUrl =  "https://st.kp.yandex.net/images/film_iphone/iphone360_361.jpg",
//            description = "Терзаемый хронической бессонницей и отчаянно пытающийся вырваться из мучительно скучной жизни, клерк встречает некоего Тайлера Дардена," +
//                    " харизматического торговца мылом с извращенной философией. Тайлер уверен, что самосовершенствование — удел слабых, а саморазрушение — единственное," +
//                    " ради чего стоит жить. Пройдет немного времени, и вот уже главные герои лупят друг друга почем зря на стоянке перед баром, и очищающий мордобой " +
//                    "доставляет им высшее блаженство. Приобщая других мужчин к простым радостям физической жестокости, они основывают тайный Бойцовский Клуб, который имеет" +
//                    " огромный успех. Но в концовке фильма всех ждет шокирующее открытие, которое может привести к непредсказуемым событиям…",
//            genres = listOf("триллер", "драма", "криминал") )
//
//        Glide.with(binding.imageMovieBackdrop)
//            .load(film.imageUrl)
//            .into(binding.imageMovieBackdrop)
//
//        Glide.with(binding.imagePoster)
//            .load(film.imageUrl)
//            .into(binding.imagePoster)
//
//        binding.filmLocalizedName.text = film.localizedName
//        binding.filmName.text = film.name
//        binding.textOverview.text = film.description
//        binding.filmYear.text = film.year.toString()
//        binding.filmRating.text = film.rating.toString()
//        var chip: Chip = Chip(binding.filmGroupGenre.context)
//        var chip2: Chip = Chip(binding.filmGroupGenre.context)
//        var chip3: Chip = Chip(binding.filmGroupGenre.context)
//        chip.setText("триллер")
//        chip.isCheckable = false
//        chip.isClickable = false
//        binding.filmGroupGenre.addView(chip)
//
//        chip2.setText("драма")
//        chip2.isCheckable = false
//        chip2.isClickable = false
//        binding.filmGroupGenre.addView(chip2)
//
//        chip3.setText("криминал")
//        chip3.isCheckable = false
//        chip3.isClickable = false
//        binding.filmGroupGenre.addView(chip3)



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