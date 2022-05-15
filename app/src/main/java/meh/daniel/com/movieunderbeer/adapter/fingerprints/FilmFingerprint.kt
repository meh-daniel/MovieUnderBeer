package meh.daniel.com.movieunderbeer.adapter.fingerprints

import android.view.LayoutInflater
import android.view.ViewGroup
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.adapter.BaseViewHolder
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.adapter.common.ItemFingerprint
import meh.daniel.com.movieunderbeer.databinding.ItemFilmBinding
import meh.daniel.com.movieunderbeer.model.entities.FilmX

class FilmFingerprint : ItemFingerprint<ItemFilmBinding, FilmX> {

    override fun isRelativeItem(item: Item) = item is FilmX

    override fun getLayoutId(): Int = R.layout.item_film

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemFilmBinding, FilmX> {
        val binding = ItemFilmBinding.inflate(layoutInflater, parent, false)
        return FilmViewHolder(binding)
    }
}

class FilmViewHolder(
    binding: ItemFilmBinding
) : BaseViewHolder<ItemFilmBinding, FilmX>(binding) {

    override fun onBind(item: FilmX) = with(binding) {
        rating.text = item.rating.toString()
        titleFilmTV.text = item.localized_name
    }

}