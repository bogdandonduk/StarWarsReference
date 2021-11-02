package proto.android.starwarsreference.core.di

import dagger.Module
import dagger.Provides
import proto.android.starwarsreference.core.api.StarWarsAPI
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
class StarWarsAPIModule {
    @Provides
    fun getAPI() : StarWarsAPI = Retrofit.Builder().baseUrl(StarWarsAPI.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(StarWarsAPI::class.java)
}