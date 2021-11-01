package proto.android.starwarsreference.core.repo

import proto.android.starwarsreference.core.api.API
import proto.android.starwarsreference.core.category.Category
import proto.android.starwarsreference.core.item.Item

interface Repo<ItemType : Item> {
    val api: API

    suspend fun fetchCategoryItems(action: (List<ItemType>) -> Unit)
}