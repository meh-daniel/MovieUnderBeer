package meh.daniel.com.movieunderbeer.adapter.base

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import meh.daniel.com.movieunderbeer.adapter.ItemTypes.CARD
import meh.daniel.com.movieunderbeer.adapter.ItemTypes.CHIP
import meh.daniel.com.movieunderbeer.adapter.ItemTypes.HEADER
import meh.daniel.com.movieunderbeer.adapter.common.AdapterClickListenerById
import meh.daniel.com.movieunderbeer.adapter.common.Item
import ru.alexmaryin.recycleronvisitor.ui.adapter.ViewHolderVisitor
import ru.alexmaryin.recycleronvisitor.ui.adapter.ViewHoldersManager

abstract class BaseListAdapter(
    private val clickListener: AdapterClickListenerById,
    private val viewHoldersManager: ViewHoldersManager
) : ListAdapter<Item, BaseListAdapter.DataViewHolder>(BaseDiffCallback()) {
    inner class DataViewHolder(
        private val binding: ViewDataBinding,
        private val holder: ViewHolderVisitor
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item, clickListener: AdapterClickListenerById) =
            holder.bind(binding, item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        LayoutInflater.from(parent.context).run {
            val holder = viewHoldersManager.getViewHolder(viewType)
            DataViewHolder(DataBindingUtil.inflate(this, holder.getLayoutId(), parent, false), holder)
        }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(getItem(position), clickListener)

    override fun getItemViewType(position: Int): Int = viewHoldersManager.getItemType(getItem(position))
}

