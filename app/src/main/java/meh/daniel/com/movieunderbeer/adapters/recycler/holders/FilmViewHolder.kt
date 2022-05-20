package meh.daniel.com.movieunderbeer.adapters.recycler.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.databinding.ItemFilmBinding
import meh.daniel.com.movieunderbeer.model.entities.films.Film

class FilmViewHolder(private val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: Film) = with(binding) {
        rating.text = item.rating.toString()
        when {
            item.imageUrl.isNullOrEmpty() -> {
                Glide.with(imageFilm)
                    .load(R.drawable.img_1)
                    .into(imageFilm)
            }
            item.imageUrl != null -> {
                Glide.with(imageFilm)
                    .load(item.imageUrl)
                    .into(imageFilm)

            }
            item.imageUrl == "null"-> {
                Glide.with(imageFilm)
                    .load(R.drawable.img_1)
                    .into(imageFilm)

            }
        }
        titleFilmTV.text = item.localizedName
    }

    companion object {
        fun create(parent: ViewGroup): FilmViewHolder {
            val inflater : LayoutInflater = LayoutInflater.from(parent.context)
            val binding: ItemFilmBinding = ItemFilmBinding.inflate(inflater, parent, false)
            return FilmViewHolder(binding)
        }
    }
}