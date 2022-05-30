package meh.daniel.com.movieunderbeer.adapter.common

import androidx.recyclerview.selection.ItemDetailsLookup

interface ItemSelection<T: String> {
    fun getItemDetails(): ItemDetailsLookup.ItemDetails<T>
}