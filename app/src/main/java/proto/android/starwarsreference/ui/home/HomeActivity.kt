package proto.android.starwarsreference.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import proto.android.starwarsreference.core.BaseActivity
import proto.android.starwarsreference.core.BaseRecyclerViewAdapter
import proto.android.starwarsreference.core.category.CategoryManager
import proto.android.starwarsreference.core.item.Item
import proto.android.starwarsreference.core.repo.*
import proto.android.starwarsreference.databinding.ActivityHomeBinding
import proto.android.starwarsreference.ui.details.DetailsActivity
import proto.android.starwarsreference.ui.details.DetailsActivityViewModel
import proto.android.starwarsreference.ui.home.di.DaggerHomeActivityViewModelComponent
import proto.android.starwarsreference.ui.home.navigation.HomeNavigationAdapter
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>(
    {
        ActivityHomeBinding.inflate(it)
    }
) {
    override var slidable = false
    @Inject lateinit var viewModel: HomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerHomeActivityViewModelComponent.create().inject(this)

        getInitializedAppBar(this, viewBinding.activityHomeToolbar)
            .modifyAsActionBar {
                it.setDisplayShowTitleEnabled(false)
            }

        viewBinding.activityHomeNavigationRecyclerView.run {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)

            adapter = HomeNavigationAdapter(this@HomeActivity, CategoryManager.getCategories(), object : BaseRecyclerViewAdapter.BaseHelper {
                override fun onItemClicked(context: Context, name: String) {
                    CategoryManager.setLastOpenCategoryName(context, name)

                    (adapter as? HomeNavigationAdapter)?.rebind()

                    viewModel.run {
                        initializeRepo()

                        load(viewBinding.activityHomeSearchbarEditText.text.toString())
                    }
                }
            })
        }

        viewModel.observe(this) {
            (viewBinding.activityHomeContentListRecyclerView.adapter as? HomeContentAdapter).run {
                if(this != null)
                    submitItems(it)
                else
                    initializeContent(it)
            }
        }

        viewBinding.activityHomeSearchbarEditText.run {
            viewModel.load(text.toString())

            setOnEditorActionListener { _, _, keyEvent ->
                keyEvent?.run {
                    if(action == EditorInfo.IME_ACTION_SEARCH)
                        viewModel.load(text.toString())
                }

                false
            }

            addTextChangedListener {
                viewModel.load(it.toString())
            }
        }
    }

    private fun initializeContent(items: List<Item>?) {
        viewBinding.activityHomeContentListRecyclerView.adapter = HomeContentAdapter(
            this@HomeActivity,
            items,
            null,
            viewBinding.activityHomeLoadingProgressBar,
            viewBinding.activityHomeNoItemsHintTextView
        ).apply {
            helper = object : BaseRecyclerViewAdapter.BaseHelper {
                override fun onItemClicked(context: Context, name: String) {
                    startActivity(Intent(this@HomeActivity, DetailsActivity::class.java).apply {
                        putExtra(DetailsActivityViewModel.KEY_ITEM, findItemByName(name))
                    })
                }
            }
        }
    }
}