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
import javax.inject.Inject

class CategoryFragmentViewModel @Inject constructor(private val categoryRepo: Repo<out Item>) : ViewModel() {
    private val _itemsLive: MutableLiveData<List<Item>> = MutableLiveData()

    val itemsLive = _itemsLive as LiveData<List<Item>>

    fun observe(lifecycleOwner: LifecycleOwner, action: (items: List<Item>) -> Unit) {
        _itemsLive.observe(lifecycleOwner) {
            action(it)
        }
    }

    fun load() {
        viewModelScope.launch(IO) {
            categoryRepo.fetchCategoryItems {
                _itemsLive.postValue(it)
            }
        }
    }

    fun search(searchQuery: String) {
        viewModelScope.launch(IO) {
            categoryRepo.fetchCategoryItems {
                _itemsLive.postValue(
                    LiveDataToolbox.searchFilter(searchQuery, it.toMutableList())
                )
            }
        }
    }
}