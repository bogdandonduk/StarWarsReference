package proto.android.starwarsreference.core.repo

import kotlinx.coroutines.delay
import org.json.JSONObject
import proto.android.starwarsreference.core.api.API
import proto.android.starwarsreference.core.category.CategoryManager
import proto.android.starwarsreference.core.item.Starship

class StarshipsRepo private constructor(override val api: API) : Repo<Starship> {
    companion object {
        @Volatile
        private var instance: StarshipsRepo? = null

        fun getSingleton(api: API) =
            if(instance == null)
                synchronized(this) {
                    instance = StarshipsRepo(api)

                    return instance!!
                }
            else instance!!
    }

    override var loadedItems: List<Starship>? = null

    @Volatile
    override var loadingInProgress: Boolean = false

    override suspend fun fetchCategoryItems(forceLoad: Boolean, action: (List<Starship>?) -> Unit) {
        if(!forceLoad && !loadingInProgress && loadedItems != null)
            action(loadedItems!!)
        else {
            if(!loadingInProgress) {
                loadingInProgress = true

                try {
                    api.getCategory(CategoryManager.CATEGORIES.STARSHIPS.categoryName.lowercase()).subscribe {
                        action(mutableListOf<Starship>().apply {
                            JSONObject(it.charStream().readText()).getJSONArray("results").run {
                                for(i in 0 until length()) {
                                    val jsonObject = getJSONObject(i)

                                    add(
                                        Starship(
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
                                            hyperdriveRating = jsonObject.getDouble("hyperdrive_rating").toFloat(),
                                            MGLT = jsonObject.getInt("MGLT"),
                                            `class` = jsonObject.getString("starship_class"),
                                            url = jsonObject.getString("url")
                                        )
                                    )
                                }

                            }
                        }.toList())
                    }
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