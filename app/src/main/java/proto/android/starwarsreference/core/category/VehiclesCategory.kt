package proto.android.starwarsreference.core.category

import android.graphics.drawable.Drawable
import proto.android.starwarsreference.core.item.Planet
import proto.android.starwarsreference.core.item.Starship
import proto.android.starwarsreference.core.item.Vehicle

open class VehiclesCategory(
    override val name: CharSequence,
    override val icon: Drawable,
    override val accentIcon: Drawable,
) : Category<Vehicle>(name, icon, accentIcon)