package proto.android.starwarsreference.ui.category

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.viewdatabindingwrapperslib.BaseViewBindingHandlerFragment
import bogdandonduk.viewmodelwrapperslib.automatic.SingleAutomaticInitializationWithInitializationViewModelHandler
import bogdandonduk.viewmodelwrapperslib.automatic.SingleAutomaticInitializationWithInitializationViewModelHandlerActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import proto.android.starwarsreference.core.BaseRecyclerViewAdapter
import proto.android.starwarsreference.core.api.StarWarsAPI
import proto.android.starwarsreference.core.item.Planet
import proto.android.starwarsreference.core.repo.PlanetsRepo
import proto.android.starwarsreference.databinding.FragmentCategoryBinding
import proto.android.starwarsreference.ui.home.HomeActivity
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class CategoryFragment : BaseViewBindingHandlerFragment<FragmentCategoryBinding>({ layoutInflater: LayoutInflater, viewGroup: ViewGroup? ->
    FragmentCategoryBinding.inflate(layoutInflater, viewGroup, false)
}), SingleAutomaticInitializationWithInitializationViewModelHandler<CategoryFragmentViewModel> {
    override var viewModelInitialization: () -> CategoryFragmentViewModel = {
        CategoryFragmentViewModel(PlanetsRepo.getSingleton(Retrofit.Builder().baseUrl(StarWarsAPI.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(StarWarsAPI::class.java)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.fragmentCategoryListRecyclerView.run {
            (requireActivity() as HomeActivity).run activity@ {
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        this@activity.getInitializedViewModel(this@activity).setCategoryListState(this@CategoryFragment.tag!!, layoutManager?.onSaveInstanceState())
                    }

                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {  }
                })

                this@CategoryFragment.getInitializedViewModel(this).let { viewModel ->
                    viewModel.observe(viewLifecycleOwner) {
                        adapter = CategoryFragmentAdapter(
                            this,
                            it,
                            object : BaseRecyclerViewAdapter.BaseHelper {
                                override fun onItemClicked(context: Context, name: String) {

                                }
                            }
                        )
                    }

                    viewBinding.activityHomeSearchbarEditText.run {
                        viewModel.search(text.toString())

                        setOnEditorActionListener { _, _, keyEvent ->
                            if(keyEvent.action == EditorInfo.IME_ACTION_SEARCH)
                                viewModel.search(text.toString())

                            false
                        }

                        addTextChangedListener {
                            viewModel.search(it.toString())
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        (requireActivity() as HomeActivity).let {
            viewBinding.fragmentCategoryListRecyclerView.layoutManager?.onRestoreInstanceState(it.getInitializedViewModel(it).getCategoryListState(tag!!))
        }
    }
}