package proto.android.starwarsreference.core.di

import dagger.Component
import proto.android.starwarsreference.ui.category.CategoryFragmentViewModel

@Component(modules = [StarWarsAPIModule::class])
interface CategoryFragmentViewModelComponent {
    fun get() : CategoryFragmentViewModel
}