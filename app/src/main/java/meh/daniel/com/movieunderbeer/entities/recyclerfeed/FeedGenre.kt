package meh.daniel.com.movieunderbeer.entities.recyclerfeed

import meh.daniel.com.movieunderbeer.adapter.common.Item

data class FeedGenre( val title: String, var isCheck: Boolean = false) : Item