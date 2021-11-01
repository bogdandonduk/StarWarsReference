package proto.android.starwarsreference.core.item

import android.os.Parcelable
import bogdandonduk.livedatatoolboxlib.NamedItem

interface Item : NamedItem, Parcelable {
    val name: CharSequence
    val url: String?

    override fun getItemName() = name.toString()
}