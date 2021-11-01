package proto.android.starwarsreference.core.item

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Planet(
    override val name: CharSequence,

    @SerializedName("rotation_period")
    val rotationPeriod: Int,
    @SerializedName("orbital_period")
    val orbitalPeriod: Int,

    val diameter: Int,
    val climate: String,
    val gravity: String,
    val terrain: String,
    @SerializedName("surface_water")
    val surfaceWater: String,
    val population: String,

    override val url: String
) : Item