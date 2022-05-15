package meh.daniel.com.movieunderbeer.adapter.fingerprints

import android.view.LayoutInflater
import android.view.ViewGroup
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.adapter.BaseViewHolder
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.adapter.common.ItemFingerprint
import meh.daniel.com.movieunderbeer.databinding.ItemGenreBinding
import meh.daniel.com.movieunderbeer.model.entities.GenreX

class GenreFingerprint : ItemFingerprint<ItemGenreBinding, GenreX>{
    override fun isRelativeItem(item: Item) = item is GenreX

    override fun getLayoutId() = R.layout.item_genre

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemGenreBinding, GenreX> {
        val binding = ItemGenreBinding.inflate(layoutInflater, parent, false)
        return GenreViewHolder(binding)
    }
}

class GenreViewHolder(
    binding: ItemGenreBinding
) : BaseViewHolder<ItemGenreBinding, GenreX>(binding){
    override fun onBind(item: GenreX) {
        binding.genre.text = item.genres.toString()
    }

}