package meh.daniel.com.movieunderbeer.adapter.brewerysprint

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.adapter.base.BaseViewHolder
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.adapter.common.ItemBrewerysprint
import meh.daniel.com.movieunderbeer.adapter.common.ItemSelection
import meh.daniel.com.movieunderbeer.databinding.ItemGenreBinding
import meh.daniel.com.movieunderbeer.entities.recyclerfeed.FeedGenre

class GenreBrewerysprint(
    private val getInfoGenre: (FeedGenre) -> Unit
) : ItemBrewerysprint<ItemGenreBinding, FeedGenre>, ItemSelection<String> {

    override fun isRelativeItem(item: Item) = item is FeedGenre

    override fun getLayoutId() = R.layout.item_genre

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemGenreBinding, FeedGenre> {
        val binding = ItemGenreBinding.inflate(layoutInflater, parent, false)
        return GenreViewHolder(binding, getInfoGenre)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<FeedGenre>() {
        override fun areItemsTheSame(oldItem: FeedGenre, newItem: FeedGenre) = oldItem.title == oldItem.title

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FeedGenre, newItem: FeedGenre) = oldItem == oldItem
    }

    override fun getItemDetails(): ItemDetailsLookup.ItemDetails<String> {
        TODO("Not yet implemented")
    }

}


class GenreViewHolder(
    binding: ItemGenreBinding,
    val getInfoGenre: (FeedGenre) -> Unit
) : BaseViewHolder<ItemGenreBinding, FeedGenre>(binding) {

    override fun onBind(item: FeedGenre): Unit = with(binding) {
        genreTitle.text = item.title

        genreTitle.setOnClickListener {
            if (bindingAdapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
            getInfoGenre(item)
        }
    }

}