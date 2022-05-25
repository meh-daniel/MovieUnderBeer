package meh.daniel.com.movieunderbeer.adapter.brewerysprint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.adapter.base.BaseViewHolder
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.adapter.common.ItemBrewerysprint
import meh.daniel.com.movieunderbeer.databinding.ItemFilmBinding
import meh.daniel.com.movieunderbeer.entities.films.Film

class FilmBrewerysprint(
    private val getInfoFilm: (Film) -> Unit
) : ItemBrewerysprint<ItemFilmBinding, Film> {

    override fun isRelativeItem(item: Item) = item is Film

    override fun getLayoutId(): Int = R.layout.item_film

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemFilmBinding, Film> {
        val binding = ItemFilmBinding.inflate(layoutInflater, parent, false)
        return FilmViewHolder(binding, getInfoFilm)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Film, newItem: Film) = oldItem == newItem
    }

}


class FilmViewHolder(
    binding: ItemFilmBinding,
    val getInfoFilm: (Film) -> Unit
) : BaseViewHolder<ItemFilmBinding, Film>(binding) {

    override fun onBind(item: Film): Unit = with(binding) {
        filmTitleName.text = item.localizedName

        when {
            item.imageUrl.isNullOrEmpty() -> {
                Glide.with(filmBackgroundImage)
                    .load(R.drawable.img_1)
                    .into(filmBackgroundImage)
            }
            item.imageUrl != null -> {
                Glide.with(filmBackgroundImage)
                    .load(item.imageUrl)
                    .into(filmBackgroundImage)
            }
            item.imageUrl == "null"-> {
                Glide.with(filmBackgroundImage)
                    .load(R.drawable.img_1)
                    .into(filmBackgroundImage)
            }
        }

        filmView.setOnClickListener {
            if (bindingAdapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
            getInfoFilm(item)
        }
    }

}