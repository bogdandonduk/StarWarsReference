package proto.android.starwarsreference.core.repo

import kotlinx.coroutines.delay
import org.json.JSONObject
import proto.android.starwarsreference.core.api.API
import proto.android.starwarsreference.core.category.CategoryManager
import proto.android.starwarsreference.core.item.Film
import proto.android.starwarsreference.core.item.Starship
import proto.android.starwarsreference.core.item.Vehicle

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

                try {
                    api.getCategory(CategoryManager.CATEGORIES.FILMS.categoryName.lowercase()).subscribe {
                        action(mutableListOf<Film>().apply {
                            JSONObject(it.charStream().readText()).getJSONArray("results").run {
                                for(i in 0 until length()) {
                                    val jsonObject = getJSONObject(i)

                                    add(
                                        Film(
                                            name = jsonObject.getString("title"),
                                            episodeId = jsonObject.getInt("episode_id"),
                                            openingCrawl = jsonObject.getString("opening_crawl"),
                                            director = jsonObject.getString("director"),
                                            producer = jsonObject.getString("producer"),
                                            releaseDate = jsonObject.getString("release_date"),
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