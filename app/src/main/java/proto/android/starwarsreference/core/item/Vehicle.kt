package proto.android.starwarsreference.core.item

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vehicle(
    override val name: CharSequence,
    override val model: String,
    override val manufacturer: String,
    @SerializedName("cost_in_credits")
    override val costInCredits: Long,
    override val length: Float,
    @SerializedName("max_atmosphering_speed")
    override val maxAtmospheringSpeed: Int,
    override val crew: String,
    override val passengers: Int,
    @SerializedName("cargo_capacity")
    override val cargoCapacity: Long,
    override val consumables: String,
    @SerializedName("vehicle_class")
    override val `class`: String,

    override val url: String
) : VehicleItem