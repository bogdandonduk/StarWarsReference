package proto.android.starwarsreference.core.fetch

import proto.android.starwarsreference.core.base.BaseItem

interface Fetcher<ItemType : BaseItem> {
    var reattemptStrategy: RequestReattemptStrategy

    suspend fun fetchAll() : List<ItemType>?

    suspend fun fetchSpecific(intrinsicId: Long) : ItemType?

    enum class RequestReattemptStrategy(val attemptsCount: Int) {
        UNOBTRUSIVE(1),
        PREFERRED(3),
        STUBBORN(5)
    }
}