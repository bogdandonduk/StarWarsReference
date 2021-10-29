package proto.android.starwarsreference.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import bogdandonduk.appbartoolboxandroidlib.appbar.AppBar
import proto.android.starwarsreference.category.CategoryFragment
import proto.android.starwarsreference.core.base.BaseActivity
import proto.android.starwarsreference.core.base.BaseRecyclerViewAdapter
import proto.android.starwarsreference.core.categories.CategoriesManager
import proto.android.starwarsreference.core.utils.FragmentUtils
import proto.android.starwarsreference.databinding.ActivityHomeBinding
import proto.android.starwarsreference.home.navigation.HomeNavigationAdapter

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

        FragmentUtils.loadFragment(supportFragmentManager, CategoryFragment(), CategoriesManager.getLastOpenCategoryId(this).toString(), viewBinding.activityHomeContentConstraintLayout)

        viewBinding.activityHomeNavigationRecyclerView.run {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)

            adapter = HomeNavigationAdapter(this@HomeActivity, CategoriesManager.getCategories(this@HomeActivity), object : BaseRecyclerViewAdapter.BaseHelper {
                override fun onItemClicked(context: Context, id: Long) {
                    CategoriesManager.setLastOpenCategoryId(context, id)
                    (adapter as? HomeNavigationAdapter)?.rebind()

                    FragmentUtils.loadFragment(supportFragmentManager, CategoryFragment(), id.toString(), viewBinding.activityHomeContentConstraintLayout)
                }
            })
        }
    }
}