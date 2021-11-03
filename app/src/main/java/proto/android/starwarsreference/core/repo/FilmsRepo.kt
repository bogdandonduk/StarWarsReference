package proto.android.starwarsreference.core.repo

import kotlinx.coroutines.delay
import org.json.JSONObject
import proto.android.starwarsreference.core.api.API
import proto.android.starwarsreference.core.category.CategoryManager
import proto.android.starwarsreference.core.item.Film

class FilmsRepo private constructor(override val api: API) : Repo<Film> {
    companion object {
        @Volatile
        private var instance: FilmsRepo? = null

        fun getSingleton(api: API) =
            if(instance == null)
                synchronized(this) {
                    instance = FilmsRepo(api)

                    return instance!!
                }
            else instance!!
    }

    override var loadedItems: List<Film>? = null

    @Volatile
    override var loadingInProgress: Boolean = false

    override suspend fun fetchCategoryItems(forceLoad: Boolean, action: (List<Film>?) -> Unit) {
        if(!forceLoad && !loadingInProgress && loadedItems != null)
            action(loadedItems!!)
        else {
            if(!loadingInProgress) {
                loadingInProgress = true

                fun getItems(items: MutableList<Film>, pageIndex: Int = 1) {
                    try {
                        api.getCategory(CategoryManager.CATEGORIES.FILMS.categoryName.lowercase(), pageIndex).subscribe {
                            val rootJsonObject = JSONObject(it.charStream().readText())

                            items.apply {
                                rootJsonObject.getJSONArray("results").run {
                                    for (i in 0 until length()) {
                                        val jsonObject = getJSONObject(i)

                                        add(
                                            Film(
                                                name = jsonObject.getString("title"),
                                                episodeId = jsonObject.getString("episode_id"),
                                                openingCrawl = jsonObject.getString("opening_crawl"),
                                                director = jsonObject.getString("director"),
                                                producer = jsonObject.getString("producer"),
                                                releaseDate = jsonObject.getString("release_date"),
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