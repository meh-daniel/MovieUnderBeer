package meh.daniel.com.movieunderbeer.adapters.recycler.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import meh.daniel.com.movieunderbeer.adapters.recycler.base.BaseViewHolder

interface ItemFingerprint <V : ViewBinding, I : Item> {

    fun isRelativeItem(item: Item): Boolean

    @LayoutRes
    fun getLayoutId(): Int

    fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<V, I>

}