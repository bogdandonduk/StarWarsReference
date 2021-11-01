package proto.android.starwarsreference.ui.details

import android.content.Intent
import androidx.lifecycle.ViewModel
import proto.android.starwarsreference.core.item.Item

class DetailsActivityViewModel(intent: Intent) : ViewModel() {
    companion object {
        const val KEY_ITEM = "key_item"
    }

    val item: Item? = intent.getParcelableExtra(KEY_ITEM)
}