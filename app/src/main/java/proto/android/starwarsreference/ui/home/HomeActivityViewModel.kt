package proto.android.starwarsreference.ui.home

import android.os.Parcelable
import androidx.lifecycle.ViewModel

class HomeActivityViewModel : ViewModel() {
    private val categoriesListsStates = mutableMapOf<String, Parcelable?>()

    fun getCategoryListState(name: String) = categoriesListsStates[name]

    fun setCategoryListState(name: String, state: Parcelable?) {
        categoriesListsStates[name] = state
    }
}