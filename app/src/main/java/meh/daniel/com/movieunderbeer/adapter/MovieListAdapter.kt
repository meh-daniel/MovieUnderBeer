package meh.daniel.com.movieunderbeer.adapter

import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import meh.daniel.com.movieunderbeer.adapter.base.BrewerysprintAdapter
import meh.daniel.com.movieunderbeer.adapter.common.ItemBrewerysprint
import meh.daniel.com.movieunderbeer.entities.recyclerfeed.FeedGenre
import meh.daniel.com.movieunderbeer.entities.recyclerfeed.FeedHeader

class MovieListAdapter(
    fingerprints: List<ItemBrewerysprint<*, *>>
) : BrewerysprintAdapter(fingerprints) {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        (recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = getSpanSizeLookup()
    }

    private fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (currentList[position]) {
                    is FeedHeader -> 4
                    else -> 2
                }
            }
        }
    }
}