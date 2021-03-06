package proto.android.starwarsreference.core.repo

import kotlinx.coroutines.delay
import org.json.JSONObject
import proto.android.starwarsreference.core.api.API
import proto.android.starwarsreference.core.category.CategoryManager
import proto.android.starwarsreference.core.item.Person

class PeopleRepo private constructor(override val api: API) : Repo<Person> {
    companion object {
        @Volatile
        private var instance: PeopleRepo? = null

        fun getSingleton(api: API) =
            if(instance == null)
                synchronized(this) {
                    instance = PeopleRepo(api)

                    return instance!!
                }
            else instance!!
    }

    override var loadedItems: List<Person>? = null

    @Volatile
    override var loadingInProgress: Boolean = false

    override suspend fun fetchCategoryItems(forceLoad: Boolean, action: (List<Person>?) -> Unit) {
        if(!forceLoad && !loadingInProgress && loadedItems != null)
            action(loadedItems!!)
        else {
            if(!loadingInProgress) {
                loadingInProgress = true

                fun getItems(items: MutableList<Person>, pageIndex: Int = 1) {
                    api.getCategory(CategoryManager.CATEGORIES.PEOPLE.categoryName.lowercase(), pageIndex).subscribe {
                        val rootJsonObject = JSONObject(it.charStream().readText())

                        items.apply {
                            rootJsonObject.getJSONArray("results").run {
                                for (i in 0 until length()) {
                                    val jsonObject = getJSONObject(i)

                                    add(
                                        Person(
                                            name = jsonObject.getString("name"),
                                            height = jsonObject.getString("height"),
                                            mass = jsonObject.getString("mass"),
                                            hairColor = jsonObject.getString("hair_color"),
                                            skinColor = jsonObject.getString("skin_color"),
                                            eyeColor = jsonObject.getString("eye_color"),
                                            birthYear = jsonObject.getString("birth_year"),
                                            gender = jsonObject.getString("gender"),
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