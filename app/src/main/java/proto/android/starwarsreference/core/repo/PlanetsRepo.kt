package proto.android.starwarsreference.core.repo

import kotlinx.coroutines.delay
import org.json.JSONObject
import proto.android.starwarsreference.core.api.API
import proto.android.starwarsreference.core.category.CategoryManager
import proto.android.starwarsreference.core.item.Planet

class PlanetsRepo private constructor(override val api: API) : Repo<Planet> {
    companion object {
        @Volatile
        private var instance: PlanetsRepo? = null

        fun getSingleton(api: API) =
            if(instance == null)
                synchronized(this) {
                    instance = PlanetsRepo(api)

                    return instance!!
                }
            else instance!!
    }

    override var loadedItems: List<Planet>? = null

    @Volatile
    override var loadingInProgress: Boolean = false

    override suspend fun fetchCategoryItems(forceLoad: Boolean, action: (List<Planet>?) -> Unit) {
        if(!forceLoad && !loadingInProgress && loadedItems != null)
            action(loadedItems!!)
        else {
            if(!loadingInProgress) {
                loadingInProgress = true

                fun getItems(items: MutableList<Planet>, pageIndex: Int = 1) {
                    try {
                        api.getCategory(CategoryManager.CATEGORIES.PLANETS.categoryName.lowercase(), pageIndex).subscribe {
                            val rootJsonObject = JSONObject(it.charStream().readText())

                            items.apply {
                                rootJsonObject.getJSONArray("results").run {
                                    for (i in 0 until length()) {
                                        val jsonObject = getJSONObject(i)

                                        add(
                                            Planet(
                                                name = jsonObject.getString("name"),
                                                rotationPeriod = jsonObject.getString("rotation_period"),
                                                orbitalPeriod = jsonObject.getString("orbital_period"),
                                                diameter = jsonObject.getString("diameter"),
                                                climate = jsonObject.getString("climate"),
                                                gravity = jsonObject.getString("climate"),
                                                terrain = jsonObject.getString("terrain"),
                                                surfaceWater = jsonObject.getString("surface_water"),
                                                population = jsonObject.getString("population"),

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
                    } catch (thr: Throwable) {
                        action(items)
                    }
                }

                getItems(mutableListOf())

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