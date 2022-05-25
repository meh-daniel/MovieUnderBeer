package meh.daniel.com.movieunderbeer.adapter.brewerysprint

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.adapter.base.BaseViewHolder
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.adapter.common.ItemBrewerysprint
import meh.daniel.com.movieunderbeer.databinding.ItemTitleBinding
import meh.daniel.com.movieunderbeer.entities.recyclerfeed.FeedHeader

class HeaderBrewerysprint : ItemBrewerysprint<ItemTitleBinding, FeedHeader> {

    override fun isRelativeItem(item: Item) = item is FeedHeader

    override fun getLayoutId() = R.layout.item_title

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemTitleBinding, FeedHeader> {
        val binding = ItemTitleBinding.inflate(layoutInflater, parent, false)
        return TitleViewHolder(binding)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<FeedHeader>() {
        override fun areItemsTheSame(oldItem: FeedHeader, newItem: FeedHeader) = oldItem.title == oldItem.title

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FeedHeader, newItem: FeedHeader) = oldItem == oldItem
    }
}


class TitleViewHolder(
    binding: ItemTitleBinding
) : BaseViewHolder<ItemTitleBinding, FeedHeader>(binding) {

    override fun onBind(item: FeedHeader): Unit = with(binding) {
        headerTitle.text = item.title
    }

}
