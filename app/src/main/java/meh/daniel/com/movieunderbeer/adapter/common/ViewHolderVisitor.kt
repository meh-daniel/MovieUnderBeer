package ru.alexmaryin.recycleronvisitor.ui.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import meh.daniel.com.movieunderbeer.adapter.common.AdapterClickListenerById
import meh.daniel.com.movieunderbeer.adapter.common.Item

interface ViewHolderVisitor {
    fun getLayoutId() : Int
    fun acceptBinding(item: Item): Boolean
    fun bind(binding: ViewDataBinding, item: Any, clickListener: AdapterClickListenerById)
}