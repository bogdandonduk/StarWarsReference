package proto.android.starwarsreference.ui.home

import androidx.lifecycle.*
import bogdandonduk.livedatatoolboxlib.LiveDataToolbox
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proto.android.starwarsreference.core.item.Item
import proto.android.starwarsreference.core.repo.Repo
import proto.android.starwarsreference.ui.home.di.RepoModule
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor(var repo: Repo<out Item>) : ViewModel() {
    private val _itemsLive: MutableLiveData<List<Item>> = MutableLiveData()

    val itemsLive = _itemsLive as LiveData<List<Item>>

    fun observe(lifecycleOwner: LifecycleOwner, action: (items: List<Item>?) -> Unit) {
        _itemsLive.observe(lifecycleOwner) {
            action(it)
        }
    }

    fun initializeRepo() {
        repo = RepoModule.getRepo()
    }

    fun load(searchQuery: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.fetchCategoryItems {
                _itemsLive.postValue(
                    if(it != null && searchQuery != null && searchQuery.isNotEmpty())
                        LiveDataToolbox.searchFilter(searchQuery, it.toMutableList())
                    else
                        it
                )
            }
        }
    }
}