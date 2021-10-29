package proto.android.starwarsreference.core.repo

import proto.android.starwarsreference.core.base.BaseRepo
import proto.android.starwarsreference.core.fetch.Fetcher
import proto.android.starwarsreference.core.fetch.PlanetsFetcher
import proto.android.starwarsreference.core.items.Planet

class PlanetsRepo private constructor(override val fetcher: PlanetsFetcher) : BaseRepo<Planet>(fetcher) {
    companion object {
        @Volatile
        private var instance: PlanetsRepo? = null

        fun getSingleton(fetcher: PlanetsFetcher): PlanetsRepo {
            if (instance == null)
                synchronized(this) {
                    instance = PlanetsRepo(fetcher)
                }

            return instance!!
        }
    }
}