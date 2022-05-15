package meh.daniel.com.movieunderbeer.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import meh.daniel.com.movieunderbeer.adapter.common.Item

abstract class BaseViewHolder<out V : ViewBinding, in I : Item>(
    val binding: V
    ) : RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(item: I)
}