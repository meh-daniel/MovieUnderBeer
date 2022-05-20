package meh.daniel.com.movieunderbeer.model.entities.helpers

import meh.daniel.com.movieunderbeer.adapters.recycler.base.SelectableBaseListItem

data class FeedGenre (var name: String, override var isSelected: Boolean = false) :
    SelectableBaseListItem() {
    override fun getType(): Int = Type.GENRE.ordinal
}
