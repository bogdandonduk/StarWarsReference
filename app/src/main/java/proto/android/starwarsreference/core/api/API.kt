package proto.android.starwarsreference.core.api

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    val baseURL: String

    @GET("{category_name}")
    fun getCategory(@Path("category_name") categoryName: String, @Query("page") pageIndex: Int = 1) : Flowable<ResponseBody>

}