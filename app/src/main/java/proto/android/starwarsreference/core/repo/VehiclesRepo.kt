package proto.android.starwarsreference.core.repo

import kotlinx.coroutines.delay
import org.json.JSONObject
import proto.android.starwarsreference.core.api.API
import proto.android.starwarsreference.core.category.CategoryManager
import proto.android.starwarsreference.core.item.Vehicle

class VehiclesRepo private constructor(override val api: API) : Repo<Vehicle> {
    companion object {
        @Volatile
        private var instance: VehiclesRepo? = null

        fun getSingleton(api: API) =
            if(instance == null)
                synchronized(this) {
                    instance = VehiclesRepo(api)

                    return instance!!
                }
            else instance!!
    }

    override var loadedItems: List<Vehicle>? = null

    @Volatile
    override var loadingInProgress: Boolean = false

    override suspend fun fetchCategoryItems(forceLoad: Boolean, action: (List<Vehicle>?) -> Unit) {
        if(!forceLoad && !loadingInProgress && loadedItems != null)
            action(loadedItems!!)
        else {
            if(!loadingInProgress) {
                loadingInProgress = true

                fun getItems(items: MutableList<Vehicle>, pageIndex: Int = 1) {
                    api.getCategory(CategoryManager.CATEGORIES.VEHICLES.categoryName.lowercase(), pageIndex).subscribe {
                        val rootJsonObject = JSONObject(it.charStream().readText())

                        items.apply {
                            rootJsonObject.getJSONArray("results").run {
                                for (i in 0 until length()) {
                                    val jsonObject = getJSONObject(i)

                                    add(
                                        Vehicle(
                                            name = jsonObject.getString("name"),
                                            model = jsonObject.getString("model"),
                                            manufacturer = jsonObject.getString("manufacturer"),
                                            costInCredits = jsonObject.getString("cost_in_credits"),
                                            length = jsonObject.getString("length"),
                                            maxAtmospheringSpeed = jsonObject.getString("max_atmosphering_speed"),
                                            crew = jsonObject.getString("crew"),
                                            passengers = jsonObject.getString("passengers"),
                                            cargoCapacity = jsonObject.getString("cargo_capacity"),
                                            consumables = jsonObject.getString("consumables"),
                                            `class` = jsonObject.getString("vehicle_class"),
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