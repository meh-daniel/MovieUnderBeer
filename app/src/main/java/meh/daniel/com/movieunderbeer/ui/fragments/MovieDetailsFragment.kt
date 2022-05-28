package meh.daniel.com.movieunderbeer.ui.fragments

import android.os.Bundle
 import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import meh.daniel.com.movieunderbeer.databinding.ContentDetailsMovieBinding
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieDetailsBinding
import meh.daniel.com.movieunderbeer.databinding.FragmentMovieListBinding
import meh.daniel.com.movieunderbeer.entities.films.Film
import meh.daniel.com.movieunderbeer.mvp.presenters.MovieDetailsPresenter
import meh.daniel.com.movieunderbeer.mvp.view.MovieDetailsView
import meh.daniel.com.movieunderbeer.ui.base.BaseFragment
import moxy.presenter.InjectPresenter

class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>(), MovieDetailsView {

    @InjectPresenter
    lateinit var movieDetailsPresenter: MovieDetailsPresenter

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMovieDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency()

        binding.toolbar.title = "Dangeon masters"
        var film = Film(id = 42664, localizedName = "Бойцовский клуб", name = "Fight Club",
            year = 1999, rating = 8.656, imageUrl =  "https://st.kp.yandex.net/images/film_iphone/iphone360_361.jpg",
            description = "Терзаемый хронической бессонницей и отчаянно пытающийся вырваться из мучительно скучной жизни, клерк встречает некоего Тайлера Дардена," +
                    " харизматического торговца мылом с извращенной философией. Тайлер уверен, что самосовершенствование — удел слабых, а саморазрушение — единственное," +
                    " харизматического торговца мылом с извращенной философией. Тайлер уверен, что самосовершенствование — удел слабых, а саморазрушение — единственное," +
                    " харизматического торговца мылом с извращенной философией. Тайлер уверен, что самосовершенствование — удел слабых, а саморазрушение — единственное," +
                    " харизматического торговца мылом с извращенной философией. Тайлер уверен, что самосовершенствование — удел слабых, а саморазрушение — единственное," +
                    " харизматического торговца мылом с извращенной философией. Тайлер уверен, что самосовершенствование — удел слабых, а саморазрушение — единственное," +
                    " харизматического торговца мылом с извращенной философией. Тайлер уверен, что самосовершенствование — удел слабых, а саморазрушение — единственное," +
                    " харизматического торговца мылом с извращенной философией. Тайлер уверен, что самосовершенствование — удел слабых, а саморазрушение — единственное," +
                    " харизматического торговца мылом с извращенной философией. Тайлер уверен, что самосовершенствование — удел слабых, а саморазрушение — единственное," +
                    " харизматического торговца мылом с извращенной философией. Тайлер уверен, что самосовершенствование — удел слабых, а саморазрушение — единственное," +
                    " ради чего стоит жить. Пройдет немного времени, и вот уже главные герои лупят друг друга почем зря на стоянке перед баром, и очищающий мордобой " +
                    "доставляет им высшее блаженство. Приобщая других мужчин к простым радостям физической жестокости, они основывают тайный Бойцовский Клуб, который имеет" +
                    " огромный успех. Но в концовке фильма всех ждет шокирующее открытие, которое может привести к непредсказуемым событиям…",
            genres = listOf("триллер", "драма", "криминал") )

        Glide.with(binding.imageMovieBackdrop)
            .load(film.imageUrl)
            .into(binding.imageMovieBackdrop)

        Glide.with(binding.includeContent.filmPoster)
            .load(film.imageUrl)
            .into(binding.includeContent.filmPoster)

        binding.includeContent.filmTitleLocalizedName.text = film.localizedName
        binding.includeContent.filmTitleName.text = film.name
        binding.includeContent.textOverview.text = film.description
        binding.includeContent.filmYear.text = film.year.toString()
        binding.includeContent.filmRating.text = film.rating.toString()
        var chip: Chip = Chip(binding.includeContent.filmGroupGenre.context)
        var chip2: Chip = Chip(binding.includeContent.filmGroupGenre.context)
        var chip3: Chip = Chip(binding.includeContent.filmGroupGenre.context)
        chip.setText("триллер")
        chip.isCheckable = false
        chip.isClickable = false
        binding.includeContent.filmGroupGenre.addView(chip)

        chip2.setText("драма")
        chip2.isCheckable = false
        chip2.isClickable = false
        binding.includeContent.filmGroupGenre.addView(chip2)

        chip3.setText("криминал")
        chip3.isCheckable = false
        chip3.isClickable = false
        binding.includeContent.filmGroupGenre.addView(chip3)



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