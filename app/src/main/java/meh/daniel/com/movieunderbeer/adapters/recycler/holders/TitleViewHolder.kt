package meh.daniel.com.movieunderbeer.adapters.recycler.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meh.daniel.com.movieunderbeer.databinding.ItemTitleBinding
import meh.daniel.com.movieunderbeer.model.entities.helpers.FeedTitle

class TitleViewHolder(private val binding: ItemTitleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(titleListItem: FeedTitle) {
        binding.tvFeedTitle.text = titleListItem.title
    }

    companion object {
        fun create(parent: ViewGroup): TitleViewHolder {
            val inflate : LayoutInflater = LayoutInflater.from(parent.context)
            val binding : ItemTitleBinding = ItemTitleBinding.inflate(inflate, parent, false)
            return TitleViewHolder(binding)
        }
    }
}
