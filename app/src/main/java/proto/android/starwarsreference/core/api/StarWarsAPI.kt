package proto.android.starwarsreference.core.api

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsAPI : API {
    companion object {
        const val BASE_URL = "https://swapi.dev/api/"
    }

    @GET("{category_name}")
    override fun getCategory(@Path("category_name") categoryName: String, @Query("page") pageIndex: Int) : Flowable<ResponseBody>
}