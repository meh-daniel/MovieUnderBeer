package meh.daniel.com.movieunderbeer.presentation.adapter.brewerysprint

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.presentation.adapter.base.BaseViewHolder
import meh.daniel.com.movieunderbeer.presentation.adapter.common.Item
import meh.daniel.com.movieunderbeer.presentation.adapter.common.ItemBrewerysprint
import meh.daniel.com.movieunderbeer.databinding.ItemHeaderBinding
import meh.daniel.com.movieunderbeer.domain.entities.recyclerfeed.FeedHeader

class HeaderBrewerysprint : ItemBrewerysprint<ItemHeaderBinding, FeedHeader> {

    override fun isRelativeItem(item: Item) = item is FeedHeader

    override fun getLayoutId() = R.layout.item_header

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemHeaderBinding, FeedHeader> {
        val binding = ItemHeaderBinding.inflate(layoutInflater, parent, false)
        return HeaderViewHolder(binding)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<FeedHeader>() {
        override fun areItemsTheSame(oldItem: FeedHeader, newItem: FeedHeader) = oldItem.title == newItem.title

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FeedHeader, newItem: FeedHeader) = oldItem == newItem
    }

}


class HeaderViewHolder(
    binding: ItemHeaderBinding
) : BaseViewHolder<ItemHeaderBinding, FeedHeader>(binding) {

    override fun onBind(item: FeedHeader): Unit = with(binding) {
        headerTitle.text = item.title
    }

}
