package proto.android.starwarsreference.core.item

import bogdandonduk.livedatatoolboxlib.NamedItem

interface Item : NamedItem {
    val name: CharSequence
    val url: String?

    override fun getItemName() = name.toString()
}