package proto.android.starwarsreference.core.item

interface VehicleItem : Item {
    override val name: CharSequence
    val model: String
    val manufacturer: String
    val costInCredits: String
    val length: String
    val maxAtmospheringSpeed: String
    val crew: String
    val passengers: String
    val cargoCapacity: String
    val consumables: String
    val `class`: String
}