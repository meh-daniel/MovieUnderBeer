package meh.daniel.com.movieunderbeer.adapter.common

import androidx.recyclerview.widget.DiffUtil

interface ViewHolderDiffUtils<I: Item> {
    fun getDiffUtil(): DiffUtil.ItemCallback<I>
}