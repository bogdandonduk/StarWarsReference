package proto.android.starwarsreference.core.item

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Species(
    override val name: CharSequence,
    val classification: String,
    val designation: String,
    @SerializedName("average_height")
    val averageHeight: String,

    @SerializedName("skin_colors")
    val skinColors: String,
    @SerializedName("hair_colors")
    val hairColors: String,
    @SerializedName("eye_colors")
    val eyeColors: String,

    @SerializedName("average_lifespan")
    val averageLifespan: String,
    val language: String,

    override val url: String
) : Item