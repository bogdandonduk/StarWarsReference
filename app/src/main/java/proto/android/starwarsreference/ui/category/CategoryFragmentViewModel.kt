package proto.android.starwarsreference.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import bogdandonduk.livedatatoolboxlib.LiveDataToolbox
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import proto.android.starwarsreference.core.item.Item
import proto.android.starwarsreference.core.repo.Repo

class CategoryFragmentViewModel(val categoryRepo: Repo<out Item>) : ViewModel() {
    private val itemsLive: MutableLiveData<List<Item>> = MutableLiveData()

    fun getItemsLive() = itemsLive as LiveData<List<Item>>

    fun observe(lifecycleOwner: LifecycleOwner, action: (items: List<Item>) -> Unit) {
        itemsLive.observe(lifecycleOwner) {
            action(it)
        }
    }

    fun load() {
        viewModelScope.launch(IO) {
            categoryRepo.fetchCategoryItems {
                itemsLive.postValue(it)
            }
        }
    }

    fun search(searchQuery: String) {
        itemsLive.value?.run {
            itemsLive.postValue(
                LiveDataToolbox.searchFilter(searchQuery, toMutableList())
            )
        }
    }
}