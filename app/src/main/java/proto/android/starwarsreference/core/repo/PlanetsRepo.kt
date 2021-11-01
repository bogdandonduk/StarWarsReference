package proto.android.starwarsreference.core.repo

import kotlinx.coroutines.delay
import org.json.JSONObject
import proto.android.starwarsreference.core.api.API
import proto.android.starwarsreference.core.category.CategoryManager
import proto.android.starwarsreference.core.item.Planet

class PlanetsRepo(override val api: API) : Repo<Planet> {
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

    override suspend fun fetchCategoryItems(forceLoad: Boolean, action: (List<Planet>) -> Unit) {
        if(!forceLoad && !loadingInProgress && loadedItems != null)
            action(loadedItems!!)
        else {
            if(!loadingInProgress) {
                loadingInProgress = true

                api.getCategory(CategoryManager.CATEGORIES.PLANETS.categoryName.lowercase()).subscribe {
                    action(mutableListOf<Planet>().apply {
                        JSONObject(it.charStream().readText()).getJSONArray("results").run {
                            for(i in 0 until length()) {
                                val jsonObject = getJSONObject(i)

                                add(
                                    Planet(
                                        name = jsonObject.getString("name"),
                                        rotationPeriod = jsonObject.getInt("rotation_period"),
                                        orbitalPeriod = jsonObject.getInt("orbital_period"),
                                        diameter = jsonObject.getInt("diameter"),
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
                    }.toList())
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