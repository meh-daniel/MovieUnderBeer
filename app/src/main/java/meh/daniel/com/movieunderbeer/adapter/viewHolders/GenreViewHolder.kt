package meh.daniel.com.movieunderbeer.adapter.viewHolders

import androidx.databinding.ViewDataBinding
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.adapter.common.AdapterClickListenerById
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.databinding.ItemGenreBinding
import meh.daniel.com.movieunderbeer.entities.helpers.FeedGenre
import ru.alexmaryin.recycleronvisitor.ui.adapter.ViewHolderVisitor

class GenreViewHolder : ViewHolderVisitor {
    override fun getLayoutId() = R.layout.item_genre

    override fun acceptBinding(item: Item): Boolean = item is FeedGenre

    override fun bind(
        binding: ViewDataBinding,
        item: Any,
        clickListener: AdapterClickListenerById
    ) {
        (binding as ItemGenreBinding).genreItem = item as FeedGenre
    }
}