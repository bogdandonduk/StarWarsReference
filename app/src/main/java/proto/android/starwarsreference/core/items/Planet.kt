package proto.android.starwarsreference.core.items

import proto.android.starwarsreference.core.base.BaseItem

data class Planet(
    override val intrinsicId: Long, 
    override val name: CharSequence,
    val rotationPeriod: Int,
    val orbitalPeriod: Int,
    val diameter: Int,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val surfaceWater: Int,
    val population: Long,
) : BaseItem