package meh.daniel.com.movieunderbeer.presentation.ui.movielist.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import meh.daniel.com.movieunderbeer.presentation.adapter.BrewerysprintAdapter
import meh.daniel.com.movieunderbeer.presentation.adapter.common.ItemBrewerysprint
import meh.daniel.com.movieunderbeer.domain.entities.films.Film
import meh.daniel.com.movieunderbeer.domain.entities.recyclerfeed.FeedGenreGroup
import meh.daniel.com.movieunderbeer.domain.entities.recyclerfeed.FeedHeader

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
                    is FeedGenreGroup -> 4
                    is Film -> 2
                    else -> 2
                }
            }
        }
    }

}