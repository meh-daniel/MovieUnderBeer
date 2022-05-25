package meh.daniel.com.movieunderbeer.adapter.viewHolders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.adapter.common.AdapterClickListenerById
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.adapter.common.ViewHolderDiffUtils
import meh.daniel.com.movieunderbeer.databinding.ItemGenreBinding
import meh.daniel.com.movieunderbeer.entities.films.Film
import meh.daniel.com.movieunderbeer.entities.helpers.FeedGenre
import meh.daniel.com.movieunderbeer.entities.helpers.FeedHeader
import ru.alexmaryin.recycleronvisitor.ui.adapter.ViewHolderVisitor
import java.util.*

class GenreViewHolder : ViewHolderVisitor, ViewHolderDiffUtils<FeedGenre> {
    override fun getLayoutId() = R.layout.item_genre

    override fun acceptBinding(item: Item): Boolean = item is FeedGenre

    override fun bind(
        binding: ViewDataBinding,
        item: Any,
        clickListener: AdapterClickListenerById
    ) {
        (binding as ItemGenreBinding).genreItem = item as FeedGenre
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<FeedGenre>() {
        override fun areItemsTheSame(oldItem: FeedGenre, newItem: FeedGenre) = oldItem.name == oldItem.name

        override fun areContentsTheSame(oldItem: FeedGenre, newItem: FeedGenre) = oldItem == oldItem
    }
}