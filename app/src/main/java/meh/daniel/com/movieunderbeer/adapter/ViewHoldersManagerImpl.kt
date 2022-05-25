package meh.daniel.com.movieunderbeer.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import meh.daniel.com.movieunderbeer.adapter.common.Item
import meh.daniel.com.movieunderbeer.adapter.common.ViewHolderDiffUtils
import meh.daniel.com.movieunderbeer.databinding.ItemGenreBinding
import meh.daniel.com.movieunderbeer.entities.films.Film
import ru.alexmaryin.recycleronvisitor.ui.adapter.ViewHolderVisitor
import ru.alexmaryin.recycleronvisitor.ui.adapter.ViewHoldersManager

class ViewHoldersManagerImpl : ViewHoldersManager {

    private val holdersMap = emptyMap<Int, ViewHolderVisitor>().toMutableMap()

    override fun registerViewHolder(itemType: Int, viewHolder: ViewHolderVisitor) {
        holdersMap += itemType to viewHolder
    }

    override fun getItemType(item: Item): Int {
        holdersMap.forEach { (itemType, holder) ->
            if(holder.acceptBinding(item)) return itemType
        }
        return ItemTypes.UNKNOWN
    }

    override fun getViewHolder(itemType: Int) = holdersMap[itemType]?: throw TypeCastException("Unknown recycler item type!")
}