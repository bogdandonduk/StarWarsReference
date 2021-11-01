package proto.android.starwarsreference.core.category

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import proto.android.starwarsreference.core.item.Item

@Parcelize
open class Category<ItemType>(
    override val name: CharSequence,
    open val icon: @RawValue Drawable,
    open val accentIcon: @RawValue Drawable,
) : Item {
    @IgnoredOnParcel
    override val url: String? = null

    @IgnoredOnParcel
    open val next: String? = null
    @IgnoredOnParcel
    open val previous: String? = null

    @IgnoredOnParcel
    @SerializedName("results")
    open val items: List<ItemType>? = null
}