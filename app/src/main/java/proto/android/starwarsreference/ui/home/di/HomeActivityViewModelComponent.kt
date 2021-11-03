package proto.android.starwarsreference.ui.home.di

import dagger.Component
import proto.android.starwarsreference.ui.home.HomeActivity
import proto.android.starwarsreference.ui.home.HomeActivityViewModel

@Component(modules = [RepoModule::class, APIModule::class])
interface HomeActivityViewModelComponent {
    fun get() : HomeActivityViewModel

    fun inject(homeActivity: HomeActivity)
}