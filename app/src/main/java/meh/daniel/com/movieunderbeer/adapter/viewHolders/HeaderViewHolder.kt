package ru.alexmaryin.recycleronvisitor.ui.viewHolders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.adapter.common.AdapterClickListenerById
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.adapter.common.ViewHolderDiffUtils
import meh.daniel.com.movieunderbeer.databinding.ItemHeaderBinding
import meh.daniel.com.movieunderbeer.entities.helpers.FeedHeader
import ru.alexmaryin.recycleronvisitor.ui.adapter.ViewHolderVisitor

class HeaderViewHolder : ViewHolderVisitor {

    override fun getLayoutId() = R.layout.item_header

    override fun acceptBinding(item: Item): Boolean = item is FeedHeader

    override fun bind(
        binding: ViewDataBinding,
        item: Any,
        clickListener: AdapterClickListenerById
    ) {
        (binding as ItemHeaderBinding).headerItem = item as FeedHeader
    }

}