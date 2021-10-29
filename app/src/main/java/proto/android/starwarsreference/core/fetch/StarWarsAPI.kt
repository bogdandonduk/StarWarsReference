package proto.android.starwarsreference.core.fetch

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsAPI {
    companion object {
        const val BASE_URL = "https://swapi.dev/"
    }

    @GET("{category_name}")
    suspend fun getCategory(@Path("category_name") categoryName: String) : Call<ResponseBody>

    @GET("{category_name}/{item_id}")
    suspend fun getItem(@Path("category_name") categoryName: String, @Path("item_id") itemId: Long) : Call<ResponseBody>
}