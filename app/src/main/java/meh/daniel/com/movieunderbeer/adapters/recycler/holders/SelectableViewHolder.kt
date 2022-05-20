package meh.daniel.com.movieunderbeer.adapters.recycler.holders
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class SelectableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(isSelected: Boolean)
}
