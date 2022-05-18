package meh.daniel.com.movieunderbeer.adapters.recycler.common.helpers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.adapters.recycler.base.BaseViewHolder
import meh.daniel.com.movieunderbeer.adapters.recycler.common.Item
import meh.daniel.com.movieunderbeer.adapters.recycler.common.ItemFingerprint
import meh.daniel.com.movieunderbeer.databinding.ItemTitleBinding
import meh.daniel.com.movieunderbeer.model.entities.helpers.FeedTitle

class TitleFingerprint : ItemFingerprint<ItemTitleBinding, FeedTitle> {
    override fun isRelativeItem(item: Item) = item is FeedTitle

    override fun getLayoutId() = R.layout.item_title

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemTitleBinding, FeedTitle> {
        val binding = ItemTitleBinding.inflate(layoutInflater, parent, false)
        return TitleViewHolder(binding)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<FeedTitle>() {
        override fun areItemsTheSame(oldItem: FeedTitle, newItem: FeedTitle) = oldItem.title == oldItem.title

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FeedTitle, newItem: FeedTitle) = oldItem == oldItem
    }
}

class TitleViewHolder(
    binding: ItemTitleBinding
) : BaseViewHolder<ItemTitleBinding, FeedTitle>(binding) {

    override fun onBind(item: FeedTitle) {
        binding.tvFeedTitle.text = item.title
    }

}
