package meh.daniel.com.movieunderbeer.adapter.common


class AdapterClickListenerById(val clickListener: (id: String) -> Unit) {
    fun onClick(id: String) = clickListener(id)
}