package meh.daniel.com.movieunderbeer.adapters.recycler
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import meh.daniel.com.movieunderbeer.adapters.recycler.base.BaseListItem
import meh.daniel.com.movieunderbeer.adapters.recycler.base.SelectableBaseListItem
import meh.daniel.com.movieunderbeer.adapters.recycler.holders.FilmViewHolder
import meh.daniel.com.movieunderbeer.adapters.recycler.holders.GenreViewHolder
//import meh.daniel.com.movieunderbeer.adapters.recycler2.holders.CharViewHolder
//import meh.daniel.com.movieunderbeer.adapters.recycler2.holders.DigitViewHolder
import meh.daniel.com.movieunderbeer.adapters.recycler.holders.SelectableViewHolder
import meh.daniel.com.movieunderbeer.adapters.recycler.holders.TitleViewHolder
import meh.daniel.com.movieunderbeer.model.entities.films.Film
import meh.daniel.com.movieunderbeer.model.entities.helpers.FeedGenre
import meh.daniel.com.movieunderbeer.model.entities.helpers.FeedTitle
import kotlin.properties.Delegates

class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<BaseListItem>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        (recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = getSpanSizeLookup()
    }

    override fun getItemViewType(position: Int) = items[position].getType()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BaseListItem.Type.TITLE.ordinal -> TitleViewHolder.create(parent)
            BaseListItem.Type.GENRE.ordinal -> GenreViewHolder.create(parent)
            BaseListItem.Type.FILM.ordinal -> FilmViewHolder.create(parent)
            else -> throw Exception(" $LOG_TAG Unknown view type exception")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GenreViewHolder -> holder.bind(items[position] as FeedGenre)
            is TitleViewHolder -> holder.bind(items[position] as FeedTitle)
            is FilmViewHolder -> holder.bind(items[position] as Film)
            else -> throw Exception(" $LOG_TAG Unknown view type exception")
        }
    }

    fun setItems(newItem: List<BaseListItem>) {
        items.apply {
            clear()
            addAll(newItem)
        }
        notifyDataSetChanged()
    }

    private fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (items[position].getType()) {
                    BaseListItem.Type.TITLE.ordinal -> SPAN_TITLE_SIZE
                    BaseListItem.Type.GENRE.ordinal -> SPAN_GENRE_SIZE
                    BaseListItem.Type.FILM.ordinal -> FILM_SPAN_SIZE
                    else -> DEFAULT_SPAN_SIZE
                }
            }
        }
    }

    companion object {
        const val SPAN_TITLE_SIZE = 4
        const val SPAN_GENRE_SIZE = 1
        const val FILM_SPAN_SIZE = 2
        private const val DEFAULT_SPAN_SIZE = 1
        private val LOG_TAG = ListAdapter::class.java.simpleName
        private const val ITEM_SELECTION_PAYLOAD = "item_selection_payload"
    }
}
