package meh.daniel.com.movieunderbeer.adapter.base

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.entities.films.Film
import meh.daniel.com.movieunderbeer.entities.helpers.FeedHeader
import ru.alexmaryin.recycleronvisitor.ui.adapter.ViewHoldersManager

class BaseDiffCallback() : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        if (oldItem::class != newItem::class) return false
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item){
        if (oldItem::class != newItem::class) return false

    }

}