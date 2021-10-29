package proto.android.starwarsreference.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.viewdatabindingwrapperslib.BaseViewBindingHandlerFragment
import bogdandonduk.viewmodelwrapperslib.automatic.SingleAutomaticInitializationWithInitializationViewModelHandler
import proto.android.starwarsreference.databinding.FragmentCategoryBinding
import proto.android.starwarsreference.home.HomeActivity

class CategoryFragment : BaseViewBindingHandlerFragment<FragmentCategoryBinding>({ layoutInflater: LayoutInflater, viewGroup: ViewGroup? ->
    FragmentCategoryBinding.inflate(layoutInflater, viewGroup, false)
}), SingleAutomaticInitializationWithInitializationViewModelHandler<CategoryFragmentViewModel> {
    override var viewModelInitialization: () -> CategoryFragmentViewModel = {
        CategoryFragmentViewModel(tag!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.fragmentCategoryListRecyclerView.run {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    (requireActivity() as HomeActivity).run {
                        getInitializedViewModel(this).setCategoryListState(getInitializedViewModel(viewModelStore).categoryId, recyclerView.layoutManager?.onSaveInstanceState())
                    }
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {  }
            })
        }
    }

    override fun onResume() {
        super.onResume()

        (requireActivity() as HomeActivity).let {
            viewBinding.fragmentCategoryListRecyclerView.layoutManager?.onRestoreInstanceState(it.getInitializedViewModel(it).getCategoryListState(getInitializedViewModel(viewModelStore).categoryId))
        }
    }
}