package proto.android.starwarsreference.category

import androidx.lifecycle.ViewModel

class CategoryFragmentViewModel(tag: String) : ViewModel() {
    val categoryId = Integer.parseInt(tag).toLong()


}