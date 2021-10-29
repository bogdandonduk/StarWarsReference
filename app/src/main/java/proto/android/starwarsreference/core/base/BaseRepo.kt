package proto.android.starwarsreference.core.base

import proto.android.starwarsreference.core.fetch.Fetcher

abstract class BaseRepo<ItemType: BaseItem>(open val fetcher: Fetcher<ItemType>) {
    val items = listOf<ItemType>()

    suspend fun load(receiver: (items: List<ItemType>?) -> Unit) {
        receiver(fetcher.fetchAll())
    }
}