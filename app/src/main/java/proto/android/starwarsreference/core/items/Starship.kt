package proto.android.starwarsreference.core.items

import proto.android.starwarsreference.core.base.BaseItem

data class Starship(
    override val intrinsicId: Long,
    override val name: CharSequence,
    val manufacturer: String,
    val cost_in_credits: Long,
    val length: Int,
    val maxAtmospheringSpeed: Int,
    val crew: String,
    val passengers: Int,
    val cargoCapacity: Int,
    val consumables: String,
    val hyperdriveRating: Float,
    val MGLT: Int,
    val starshipClass: String
) : BaseItem