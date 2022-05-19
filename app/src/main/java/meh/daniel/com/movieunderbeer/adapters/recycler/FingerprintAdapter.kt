package meh.daniel.com.movieunderbeer.adapters.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewbinding.ViewBinding
import meh.daniel.com.movieunderbeer.adapters.recycler.base.BaseViewHolder
import meh.daniel.com.movieunderbeer.adapters.recycler.common.ItemFingerprint
import meh.daniel.com.movieunderbeer.adapters.recycler.common.Item

class FingerprintAdapter(
    private val fingerprints: List<ItemFingerprint<*, *>>,
) : ListAdapter<Item, BaseViewHolder<ViewBinding, Item>>(
    FingerprintDiffUtil(fingerprints)
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewBinding, Item> {
        val inflater = LayoutInflater.from(parent.context)
        return fingerprints.find { it.getLayoutId() == viewType }
            ?.getViewHolder(inflater, parent)
            ?.let { it as BaseViewHolder<ViewBinding, Item> }
            ?: throw IllegalArgumentException("View type not found: $viewType")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, Item>, position: Int) {
        val layoutParams = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        layoutParams.isFullSpan = true
        holder.onBind(currentList[position])
    }

    override fun getItemViewType(position: Int): Int {
        val item = currentList[position]
        return fingerprints.find { it.isRelativeItem(item) }
            ?.getLayoutId()
            ?: throw IllegalArgumentException("View type not found: $item")
    }
//    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
//        super.onAttachedToRecyclerView(recyclerView)
//        (recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = getSpanSizeLookup()
//    }
//
//    private fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
//        return object : GridLayoutManager.SpanSizeLookup() {
//            override fun getSpanSize(position: Int): Int {
//                return when (fingerprints[position]) {
//                    Type.TITLE.ordinal -> SPAN_SIZE
//                    Type.DIGIT.ordinal -> DIGIT_SPAN_SIZE
//                    else -> DEFAULT_SPAN_SIZE
//                }
//            }
//        }
//    }
//    companion object {
//        const val SPAN_SIZE = 4
//        const val DIGIT_SPAN_SIZE = 2
//        private const val DEFAULT_SPAN_SIZE = 1
//        private val LOG_TAG = ListAdapter::class.java.simpleName
//        private const val ITEM_SELECTION_PAYLOAD = "item_selection_payload"
//    }
}