package meh.daniel.com.movieunderbeer.adapter.brewerysprint

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.chip.Chip
import meh.daniel.com.movieunderbeer.R
import meh.daniel.com.movieunderbeer.adapter.base.BaseViewHolder
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.adapter.common.ItemBrewerysprint
import meh.daniel.com.movieunderbeer.databinding.ItemGroupChipGenreBinding
import meh.daniel.com.movieunderbeer.entities.recyclerfeed.FeedGenre
import meh.daniel.com.movieunderbeer.entities.recyclerfeed.FeedGenreGroup


class GenreGroupBrewerysprint(
    private val getInfoGenre: (FeedGenre) -> Unit
) : ItemBrewerysprint<ItemGroupChipGenreBinding, FeedGenreGroup>{

    override fun isRelativeItem(item: Item) = item is FeedGenreGroup

    override fun getLayoutId() = R.layout.item_group_chip_genre

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemGroupChipGenreBinding, FeedGenreGroup> {
        val binding = ItemGroupChipGenreBinding.inflate(layoutInflater, parent, false)
        return GenreGroupViewHolder(binding, getInfoGenre)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<FeedGenreGroup>() {
        override fun areItemsTheSame(oldItem: FeedGenreGroup, newItem: FeedGenreGroup) = oldItem.listGenre == newItem.listGenre

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FeedGenreGroup, newItem: FeedGenreGroup) = oldItem == newItem
    }

}


class GenreGroupViewHolder(
    binding: ItemGroupChipGenreBinding,
    val getInfoGenre: (FeedGenre) -> Unit
) : BaseViewHolder<ItemGroupChipGenreBinding, FeedGenreGroup>(binding) {

    //https://music.yandex.ru/album/15820215/track/83859506
    private var wtf = 1

    override fun onBind(item: FeedGenreGroup) = with(binding) {

        if (wtf == 1){
            groupChipGenre.isSingleSelection
            for (genre in item.listGenre) {
                val chip = Chip(groupChipGenre.context)
                chip.text = genre.title
                chip.isClickable = true
                chip.isCheckable = false
                chip.setOnClickListener {
                    getInfoGenre(FeedGenre(genre.title))
                }
                groupChipGenre.addView(chip)
            }
            wtf++
        }

    }
}