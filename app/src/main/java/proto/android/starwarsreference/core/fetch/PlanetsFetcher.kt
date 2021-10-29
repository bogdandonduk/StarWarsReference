package proto.android.starwarsreference.core.fetch

import android.util.Log
import proto.android.starwarsreference.core.items.Planet
import retrofit2.Retrofit

class PlanetsFetcher : Fetcher<Planet> {
    override var reattemptStrategy: Fetcher.RequestReattemptStrategy = Fetcher.RequestReattemptStrategy.PREFERRED

    val starWarsAPI = Retrofit.Builder()
        .baseUrl(StarWarsAPI.BASE_URL)
        .build()
        .create(StarWarsAPI::class.java)

    override suspend fun fetchAll(): List<Planet>? {
        starWarsAPI.getCategory("planets").execute().run {
            if(isSuccessful)
                Log.d("TAG", "fetchAll: ${body()?.charStream()?.readText()}")
        }

        return null
    }

    override suspend fun fetchSpecific(intrinsicId: Long): Planet? {
        return null
    }
}