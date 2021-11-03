package proto.android.starwarsreference.ui.home.di

import dagger.Module
import dagger.Provides
import proto.android.starwarsreference.core.category.CategoryManager
import proto.android.starwarsreference.core.item.Item
import proto.android.starwarsreference.core.repo.Repo

@Module
class RepoModule {
    companion object {
        @Provides
        fun getRepo() : Repo<out Item> = CategoryManager.getRepoForCategory()
    }
}