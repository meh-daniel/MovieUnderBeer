package meh.daniel.com.movieunderbeer.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import meh.daniel.com.movieunderbeer.presentation.adapter.base.BaseViewHolder
import meh.daniel.com.movieunderbeer.presentation.adapter.common.Item
import meh.daniel.com.movieunderbeer.presentation.adapter.common.ItemBrewerysprint

abstract class BrewerysprintAdapter(
    private val fingerprints: List<ItemBrewerysprint<*, *>>,
) : ListAdapter<Item, BaseViewHolder<ViewBinding, Item>>(
    BrewerysprintDiffUtil(fingerprints)
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewBinding, Item> {
        val inflater = LayoutInflater.from(parent.context)
        return fingerprints.find { it.getLayoutId() == viewType }
            ?.getViewHolder(inflater, parent)
            ?.let { it as BaseViewHolder<ViewBinding, Item> }
            ?: throw IllegalArgumentException("View type not found: $viewType")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, Item>, position: Int) {
        holder.onBind(currentList[position])
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ViewBinding, Item>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNullOrEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.onBind(currentList[position], payloads)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = currentList[position]
        return fingerprints.find { it.isRelativeItem(item) }
            ?.getLayoutId()
            ?: throw IllegalArgumentException("View type not found: $item")
    }

}