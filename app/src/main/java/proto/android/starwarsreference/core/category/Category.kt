package proto.android.starwarsreference.core.category

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName
import proto.android.starwarsreference.core.item.Item

open class Category<ItemType>(
    override val name: CharSequence,
    open val icon: Drawable,
    open val accentIcon: Drawable,
) : Item {
    override val url: String? = null

    open val next: String? = null
    open val previous: String? = null

    @SerializedName("results")
    open val items: List<ItemType>? = null
}