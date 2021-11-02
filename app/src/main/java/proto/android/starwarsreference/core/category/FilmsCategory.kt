package proto.android.starwarsreference.core.category

import android.graphics.drawable.Drawable
import proto.android.starwarsreference.core.item.Film

open class FilmsCategory(
    override val name: CharSequence,
    override val icon: Drawable,
    override val accentIcon: Drawable,
) : Category<Film>(name, icon, accentIcon)