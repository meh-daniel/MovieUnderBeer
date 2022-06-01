package meh.daniel.com.movieunderbeer.adapter.brewerysprint

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
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
        override fun areItemsTheSame(oldItem: FeedGenreGroup, newItem: FeedGenreGroup) = oldItem.listGenre.size == newItem.listGenre.size

        override fun areContentsTheSame(oldItem: FeedGenreGroup, newItem: FeedGenreGroup) = true
    }

}


class GenreGroupViewHolder(
    binding: ItemGroupChipGenreBinding,
    val getInfoGenre: (FeedGenre) -> Unit
) : BaseViewHolder<ItemGroupChipGenreBinding, FeedGenreGroup>(binding) {

    //https://music.yandex.ru/album/15820215/track/83859506
    private var wtf = 1

    override fun onBind(item: FeedGenreGroup) = with(binding) {
        Log.d("xxx:", "OnBIND!!!!")
        if (wtf == 1){

            val chipGroup = ChipGroup(parentChips.context)
            chipGroup.isSingleSelection = true
            var id = 1
            for (i in item.listGenre) {
                val chip = Chip(parentChips.context)
                val chipDrawable = ChipDrawable.createFromAttributes(
                    parentChips.context,
                    null,
                    0,
                    R.style.ChipsTheme
                )
                chip.id = id
                chip.setTag(i.title)
                id++
                chip.setChipDrawable(chipDrawable)
                chip.text = i.title
                chipGroup.addView(chip)
            }

            parentChips.addView(chipGroup)
            var lastId : Int = 666
            for (index in 0 until chipGroup.childCount) {
                val chip: Chip = chipGroup.getChildAt(index) as Chip
                chip.setOnCheckedChangeListener { view, isChecked ->
                    Log.d("xxx:", "${isChecked.toString()}")
                    if(isChecked) {
                        getInfoGenre(FeedGenre(view.tag.toString()))
                    }
                    else{
                        getInfoGenre(FeedGenre(""))
                    }
                }
            }
            wtf++
        }

    }
}