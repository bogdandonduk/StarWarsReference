package proto.android.starwarsreference.ui.home

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import proto.android.starwarsreference.ui.category.CategoryFragment
import proto.android.starwarsreference.core.BaseActivity
import proto.android.starwarsreference.core.BaseRecyclerViewAdapter
import proto.android.starwarsreference.core.category.CategoryManager
import proto.android.starwarsreference.core.utils.FragmentUtils
import proto.android.starwarsreference.databinding.ActivityHomeBinding
import proto.android.starwarsreference.ui.home.navigation.HomeNavigationAdapter

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>(
    {
        ActivityHomeBinding.inflate(it)
    },
    {
        HomeActivityViewModel()
    }
) {
    override var slidable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getInitializedAppBar(this, viewBinding.activityHomeToolbar)
            .modifyAsActionBar {
                it.setDisplayShowTitleEnabled(false)
            }

        FragmentUtils.loadFragment(supportFragmentManager, CategoryFragment(), CategoryManager.getLastOpenCategoryName(this), viewBinding.activityHomeContentConstraintLayout)

        viewBinding.activityHomeNavigationRecyclerView.run {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)

            adapter = HomeNavigationAdapter(this@HomeActivity, CategoryManager.getCategories(), object : BaseRecyclerViewAdapter.BaseHelper {
                override fun onItemClicked(context: Context, name: String) {
                    CategoryManager.setLastOpenCategoryName(context, name)
                    (adapter as? HomeNavigationAdapter)?.rebind()

                    FragmentUtils.loadFragment(supportFragmentManager, CategoryFragment(), id.toString(), viewBinding.activityHomeContentConstraintLayout)
                }
            })
        }
    }
}