package proto.android.starwarsreference.home

import android.os.Parcelable
import androidx.lifecycle.ViewModel

class HomeActivityViewModel : ViewModel() {
    private val categoriesListsStates = mutableMapOf<Long, Parcelable?>()

    fun getCategoryListState(id: Long) = categoriesListsStates[id]

    fun setCategoryListState(id: Long, state: Parcelable?) {
        categoriesListsStates[id] = state
    }
}