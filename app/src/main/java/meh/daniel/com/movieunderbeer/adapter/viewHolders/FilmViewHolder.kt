package meh.daniel.com.movieunderbeer.adapter.viewHolders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.adapter.common.AdapterClickListenerById
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.databinding.ItemFilmBinding
import meh.daniel.com.movieunderbeer.entities.films.Film
import ru.alexmaryin.recycleronvisitor.ui.adapter.ViewHolderVisitor

class FilmViewHolder : ViewHolderVisitor {

    override fun getLayoutId() = R.layout.item_film

    override fun acceptBinding(item: Item): Boolean = item is Film

    override fun bind(binding: ViewDataBinding, item: Any, clickListener: AdapterClickListenerById) {
        with(binding as ItemFilmBinding) {
            film = item as Film
        }
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<Item> {
        TODO("Not yet implemented")
    }

}