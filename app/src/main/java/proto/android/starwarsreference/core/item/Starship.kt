package proto.android.starwarsreference.core.item

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Starship(
    override val name: CharSequence,
    override val model: String,
    override val manufacturer: String,
    @SerializedName("cost_in_credits")
    override val costInCredits: String,
    override val length: String,
    @SerializedName("max_atmosphering_speed")
    override val maxAtmospheringSpeed: String,
    override val crew: String,
    override val passengers: String,
    @SerializedName("cargo_capacity")
    override val cargoCapacity: String,
    override val consumables: String,
    @SerializedName("hyperdrive_rating")
    val hyperdriveRating: String,
    val MGLT: String,
    @SerializedName("starship_class")
    override val `class`: String,

    override val url: String
) : VehicleItem