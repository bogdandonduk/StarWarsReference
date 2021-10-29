package proto.android.starwarsreference.core.items

import proto.android.starwarsreference.core.base.BaseItem

data class Person(
    override val intrinsicId: Long, 
    override val name: CharSequence,
    val height: Int,
    val mass: Int,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String
) : BaseItem