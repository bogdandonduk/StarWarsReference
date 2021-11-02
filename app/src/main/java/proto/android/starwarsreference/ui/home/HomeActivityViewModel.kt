package proto.android.starwarsreference.ui.home

import androidx.lifecycle.*
import bogdandonduk.livedatatoolboxlib.LiveDataToolbox
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proto.android.starwarsreference.core.item.Item
import proto.android.starwarsreference.core.repo.Repo
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor(private var repo: Repo<out Item>) : ViewModel() {
    private val _itemsLive: MutableLiveData<List<Item>> = MutableLiveData()

    val itemsLive = _itemsLive as LiveData<List<Item>>

    fun setRepo(repo: Repo<out Item>) {
        this.repo = repo
    }

    fun observe(lifecycleOwner: LifecycleOwner, action: (items: List<Item>) -> Unit) {
        _itemsLive.observe(lifecycleOwner) {
            action(it)
        }
    }

    fun load(searchQuery: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.fetchCategoryItems {
                _itemsLive.postValue(
                    if(searchQuery != null && searchQuery.isNotEmpty())
                        LiveDataToolbox.searchFilter(searchQuery, it.toMutableList())
                    else
                        it
                )
            }
        }
    }
}