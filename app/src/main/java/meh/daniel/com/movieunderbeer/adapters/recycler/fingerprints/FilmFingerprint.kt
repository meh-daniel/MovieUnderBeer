package meh.daniel.com.movieunderbeer.adapters.recycler.fingerprints

import android.view.LayoutInflater
import android.view.ViewGroup
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.adapters.recycler.base.BaseViewHolder
import meh.daniel.com.movieunderbeer.adapters.recycler.common.Item
import meh.daniel.com.movieunderbeer.adapters.recycler.common.ItemFingerprint
import meh.daniel.com.movieunderbeer.databinding.ItemFilmBinding
import meh.daniel.com.movieunderbeer.model.entities.films.Film

class FilmFingerprint : ItemFingerprint<ItemFilmBinding, Film> {

    override fun isRelativeItem(item: Item) = item is Film

    override fun getLayoutId(): Int = R.layout.item_film

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemFilmBinding, Film> {
        val binding = ItemFilmBinding.inflate(layoutInflater, parent, false)
        return FilmViewHolder(binding)
    }
}

class FilmViewHolder(
    binding: ItemFilmBinding
) : BaseViewHolder<ItemFilmBinding, Film>(binding) {

    override fun onBind(item: Film) = with(binding) {
        rating.text = item.rating.toString()
        titleFilmTV.text = item.localizedName
    }

}