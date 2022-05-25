package ru.alexmaryin.recycleronvisitor.ui.adapter

import meh.daniel.com.movieunderbeer.adapter.common.Item

interface ViewHoldersManager {
    fun registerViewHolder(itemType: Int, viewHolder: ViewHolderVisitor)
    fun getItemType(item: Item): Int
    fun getViewHolder(itemType: Int): ViewHolderVisitor
}