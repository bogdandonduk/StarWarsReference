package proto.android.starwarsreference.ui.home.di

import dagger.Module
import dagger.Provides
import proto.android.starwarsreference.core.api.API
import proto.android.starwarsreference.core.api.StarWarsAPI
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
class APIModule {
    companion object {
        @Provides
        fun getAPI() : API = Retrofit.Builder().baseUrl(StarWarsAPI.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(StarWarsAPI::class.java)
    }
}