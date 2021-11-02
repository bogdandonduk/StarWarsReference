package proto.android.starwarsreference.core.category

import android.graphics.drawable.Drawable
import proto.android.starwarsreference.core.item.Starship

open class StarshipsCategory(
    override val name: CharSequence,
    override val icon: Drawable,
    override val accentIcon: Drawable,
) : Category<Starship>(name, icon, accentIcon)