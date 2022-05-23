package meh.daniel.com.movieunderbeer.adapter

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import meh.daniel.com.movieunderbeer.adapter.base.BaseListAdapter
import meh.daniel.com.movieunderbeer.adapter.common.AdapterClickListenerById
import ru.alexmaryin.recycleronvisitor.ui.adapter.ViewHoldersManager

class MovieListAdapter(
    private val clickListener: AdapterClickListenerById,
    private val viewHoldersManager: ViewHoldersManager
) : BaseListAdapter(clickListener, viewHoldersManager) {


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        try {
            (recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = getSpanSizeLookup()
        } catch (e : Exception){
            Log.d("xxx:", e.toString())
        }
    }

    private fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (getItemViewType(position)) {
                    ItemTypes.HEADER -> 4
                    ItemTypes.CARD -> 2
                    ItemTypes.CHIP -> 2
                    else -> 2
                }
            }
        }
    }

}