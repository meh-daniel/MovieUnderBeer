package meh.daniel.com.movieunderbeer.adapters.recycler.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import meh.daniel.com.movieunderbeer.adapters.recycler.common.Item

abstract class BaseViewHolder<out V : ViewBinding, I : Item>(
    val binding: V
) : RecyclerView.ViewHolder(binding.root) {

    lateinit var item: I

    open fun onBind(item: I) {
        this.item = item
    }

}