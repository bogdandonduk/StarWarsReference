package proto.android.starwarsreference.core.repo

import proto.android.starwarsreference.core.api.API
import proto.android.starwarsreference.core.category.Category
import proto.android.starwarsreference.core.item.Item
import proto.android.starwarsreference.core.item.Planet

interface Repo<ItemType : Item> {
    companion object {
        const val WAITING_DELAY_MILLIS = 50L
    }

    val api: API

    var loadedItems: List<ItemType>?

    var loadingInProgress: Boolean

    suspend fun fetchCategoryItems(forceLoad: Boolean = false, action: (List<ItemType>) -> Unit)
}