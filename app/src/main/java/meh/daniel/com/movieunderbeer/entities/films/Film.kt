package meh.daniel.com.movieunderbeer.entities.films

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import meh.daniel.com.movieunderbeer.adapter.common.Item

data class Film(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("localized_name")
    val localizedName: String? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("year")
    val year: Int? = null,
    @SerializedName("rating")
    val rating: Double? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("genres")
    val genres: List<String>? = null
) : Item, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString().toString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(localizedName)
        parcel.writeString(name)
        parcel.writeValue(year)
        parcel.writeValue(rating)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
        parcel.writeStringList(genres)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(parcel: Parcel): Film {
            return Film(parcel)
        }

        override fun newArray(size: Int): Array<Film?> {
            return arrayOfNulls(size)
        }
    }
}