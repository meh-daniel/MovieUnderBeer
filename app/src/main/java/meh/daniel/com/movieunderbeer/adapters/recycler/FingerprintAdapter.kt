package meh.daniel.com.movieunderbeer.adapters.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import meh.daniel.com.movieunderbeer.adapters.recycler.base.BaseViewHolder
import meh.daniel.com.movieunderbeer.adapters.recycler.common.ItemFingerprint
import meh.daniel.com.movieunderbeer.adapters.recycler.common.Item

class FingerprintAdapter(
    private val fingerprints: List<ItemFingerprint<*, *>>,
) : RecyclerView.Adapter<BaseViewHolder<ViewBinding, Item>>() {

    private val items = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewBinding, Item> {
        val inflater = LayoutInflater.from(parent.context)
        return fingerprints.find { it.getLayoutId() == viewType }
            ?.getViewHolder(inflater, parent)
            ?.let { it as BaseViewHolder<ViewBinding, Item> }
            ?: throw IllegalArgumentException("View type not found: $viewType")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, Item>, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return fingerprints.find { it.isRelativeItem(item) }
            ?.getLayoutId()
            ?: throw IllegalArgumentException("View type not found: $item")
    }

    fun setItems(newItems: List<Item>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addItem(newItem: List<Item>){
        items.addAll(newItem)
        notifyDataSetChanged()
    }

}