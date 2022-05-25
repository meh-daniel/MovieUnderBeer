package meh.daniel.com.movieunderbeer.adapter.common

interface Item {
    val name: String
    override fun equals(other: Any?): Boolean
}