package meh.daniel.com.movieunderbeer.model.entities.helpers

import meh.daniel.com.movieunderbeer.adapters.recycler.base.BaseListItem

class FeedTitle (
        val title: String
        ) : BaseListItem() {
        override fun getType() = Type.TITLE.ordinal
}