package meh.daniel.com.movieunderbeer.adapters.recycler.holders

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import meh.daniel.com.movieunderbeer.databinding.ItemGenreBinding
import meh.daniel.com.movieunderbeer.model.entities.helpers.FeedGenre

class GenreViewHolder(private val binding: ItemGenreBinding) : SelectableViewHolder(binding.root) {

    fun bind(genre: FeedGenre) {
        with(genre) {
            binding.genre.text = name
            bind(isSelected)
        }
    }

    override fun bind(isSelected: Boolean) {
        binding.genre.setBackgroundColor(if (isSelected) Color.BLUE else Color.WHITE)
    }

    companion object {
        fun create(parent: ViewGroup): GenreViewHolder {
            val inflater : LayoutInflater = LayoutInflater.from(parent.context)
            val binding: ItemGenreBinding = ItemGenreBinding.inflate(inflater, parent, false)
            return GenreViewHolder(binding)
        }
    }
}
