package proto.android.starwarsreference.core.base

import bogdandonduk.livedatatoolboxlib.NamedItem

interface BaseItem : NamedItem {
    val intrinsicId: Long
    val name: CharSequence

    override fun getItemName() = name.toString()
}