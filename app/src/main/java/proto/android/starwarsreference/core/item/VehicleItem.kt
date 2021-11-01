package proto.android.starwarsreference.core.item

interface VehicleItem : Item {
    override val name: CharSequence
    val model: String
    val manufacturer: String
    val costInCredits: Long
    val length: Float
    val maxAtmospheringSpeed: Int
    val crew: String
    val passengers: Int
    val cargoCapacity: Long
    val consumables: String
    val `class`: String
}