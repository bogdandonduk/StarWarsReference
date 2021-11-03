package proto.android.starwarsreference.core.repo

import kotlinx.coroutines.delay
import org.json.JSONObject
import proto.android.starwarsreference.core.api.API
import proto.android.starwarsreference.core.category.CategoryManager
import proto.android.starwarsreference.core.item.Species

class SpeciesRepo private constructor(override val api: API) : Repo<Species> {
    companion object {
        @Volatile
        private var instance: SpeciesRepo? = null

        fun getSingleton(api: API) =
            if(instance == null)
                synchronized(this) {
                    instance = SpeciesRepo(api)

                    return instance!!
                }
            else instance!!
    }

    override var loadedItems: List<Species>? = null

    @Volatile
    override var loadingInProgress: Boolean = false

    override suspend fun fetchCategoryItems(forceLoad: Boolean, action: (List<Species>?) -> Unit) {
        if(!forceLoad && !loadingInProgress && loadedItems != null)
            action(loadedItems!!)
        else {
            if(!loadingInProgress) {
                loadingInProgress = true

                fun getItems(items: MutableList<Species>, pageIndex: Int = 1) {
                    api.getCategory(CategoryManager.CATEGORIES.SPECIES.categoryName.lowercase(), pageIndex).subscribe {
                        val rootJsonObject = JSONObject(it.charStream().readText())

                        items.apply {
                            rootJsonObject.getJSONArray("results").run {
                                for (i in 0 until length()) {
                                    val jsonObject = getJSONObject(i)

                                    add(
                                        Species(
                                            name = jsonObject.getString("name"),
                                            classification = jsonObject.getString("classification"),
                                            designation = jsonObject.getString("designation"),
                                            averageHeight = jsonObject.getString("average_height"),
                                            hairColors = jsonObject.getString("hair_colors"),
                                            skinColors = jsonObject.getString("skin_colors"),
                                            eyeColors = jsonObject.getString("eye_colors"),
                                            averageLifespan = jsonObject.getString("average_lifespan"),
                                            language = jsonObject.getString("language"),
                                            url = jsonObject.getString("url")
                                        )
                                    )
                                }

                            }
                        }

                        if(rootJsonObject.getString("next") != "null")
                            getItems(items, pageIndex + 1)
                        else
                            action(items)
                    }
                }

                try {
                    getItems(mutableListOf())
                } catch (thr: Throwable) {
                    action(null)
                }

                loadingInProgress = false
            } else {
                while(loadingInProgress) {
                    delay(Repo.WAITING_DELAY_MILLIS)
                }

                loadedItems
            }
        }
    }
}