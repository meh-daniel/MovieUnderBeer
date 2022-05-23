package meh.daniel.com.movieunderbeer.adapter.base

import androidx.recyclerview.widget.DiffUtil
import meh.daniel.com.movieunderbeer.adapter.common.Item
import ru.alexmaryin.recycleronvisitor.ui.adapter.ViewHoldersManager

class BaseDiffCallback(private val viewHoldersManager: ViewHoldersManager) : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        TODO()
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        TODO("Not yet implemented")
    }
}