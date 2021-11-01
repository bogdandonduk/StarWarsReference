package proto.android.starwarsreference.core.category

import android.graphics.drawable.Drawable
import proto.android.starwarsreference.core.item.Person

open class PeopleCategory(
    override val name: CharSequence,
    override val icon: Drawable,
    override val accentIcon: Drawable,
) : Category<Person>(name, icon, accentIcon)