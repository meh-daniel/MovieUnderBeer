package meh.daniel.com.movieunderbeer.adapters.recycler.base

abstract class BaseListItem {
    abstract fun getType(): Int

    enum class Type {
        TITLE,
        GENRE,
        FILM
    }
}

abstract class SelectableBaseListItem : BaseListItem() {
    abstract var isSelected: Boolean
}