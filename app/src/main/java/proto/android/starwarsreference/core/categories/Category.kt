package proto.android.starwarsreference.core.categories

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName
import proto.android.starwarsreference.core.base.BaseItem

data class Category(override val intrinsicId: Long, override val name: CharSequence, val icon: Drawable, val accentIcon: Drawable) : BaseItem