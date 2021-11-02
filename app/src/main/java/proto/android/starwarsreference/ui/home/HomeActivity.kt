package proto.android.starwarsreference.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import proto.android.starwarsreference.core.BaseActivity
import proto.android.starwarsreference.core.BaseRecyclerViewAdapter
import proto.android.starwarsreference.core.api.StarWarsAPI
import proto.android.starwarsreference.core.category.CategoryManager
import proto.android.starwarsreference.core.item.Item
import proto.android.starwarsreference.core.repo.*
import proto.android.starwarsreference.databinding.ActivityHomeBinding
import proto.android.starwarsreference.ui.details.DetailsActivity
import proto.android.starwarsreference.ui.details.DetailsActivityViewModel
import proto.android.starwarsreference.ui.home.navigation.HomeNavigationAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>(
    {
        ActivityHomeBinding.inflate(it)
    },
    {
        HomeActivityViewModel(
            PeopleRepo.getSingleton(
                Retrofit.Builder().baseUrl(StarWarsAPI.BASE_URL).addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()).build().create(StarWarsAPI::class.java)
            )
        )
    }
) {
    override var slidable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

                    getInitializedViewModel(this@HomeActivity).run {
                        setRepo(
                            when(CategoryManager.getLastOpenCategoryName(this@HomeActivity)) {
                                CategoryManager.CATEGORIES.STARSHIPS.categoryName -> StarshipsRepo.getSingleton(Retrofit.Builder().baseUrl(StarWarsAPI.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(StarWarsAPI::class.java))
                                CategoryManager.CATEGORIES.VEHICLES.categoryName -> VehiclesRepo.getSingleton(Retrofit.Builder().baseUrl(StarWarsAPI.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(StarWarsAPI::class.java))
                                CategoryManager.CATEGORIES.PEOPLE.categoryName -> PeopleRepo.getSingleton(Retrofit.Builder().baseUrl(StarWarsAPI.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(StarWarsAPI::class.java))
                                CategoryManager.CATEGORIES.FILMS.categoryName -> FilmsRepo.getSingleton(Retrofit.Builder().baseUrl(StarWarsAPI.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(StarWarsAPI::class.java))
                                CategoryManager.CATEGORIES.SPECIES.categoryName -> SpeciesRepo.getSingleton(Retrofit.Builder().baseUrl(StarWarsAPI.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(StarWarsAPI::class.java))

                                else -> PlanetsRepo.getSingleton(Retrofit.Builder().baseUrl(StarWarsAPI.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(StarWarsAPI::class.java))
                            }
                        )

                        load(viewBinding.activityHomeSearchbarEditText.text.toString())
                    }
                }
            })
        }

        getInitializedViewModel(this).observe(this) {
            (viewBinding.activityHomeContentListRecyclerView.adapter as? HomeContentAdapter).run {
                if(this != null)
                    submitItems(it)
                else
                    initializeContent(it)
            }
        }

        viewBinding.activityHomeSearchbarEditText.run {
            getInitializedViewModel(this@HomeActivity).load(text.toString())

            setOnEditorActionListener { _, _, keyEvent ->
                keyEvent?.run {
                    if(action == EditorInfo.IME_ACTION_SEARCH)
                        getInitializedViewModel(this@HomeActivity).load(text.toString())
                }

                false
            }

            addTextChangedListener {
                getInitializedViewModel(this@HomeActivity).load(it.toString())
            }
        }
    }

    fun initializeContent(items: List<Item>) {
        viewBinding.activityHomeContentListRecyclerView.adapter = HomeContentAdapter(
            this@HomeActivity,
            items,
            object : BaseRecyclerViewAdapter.BaseHelper {
                override fun onItemClicked(context: Context, name: String) {
                    startActivity(Intent(this@HomeActivity, DetailsActivity::class.java).apply {
                        putExtra(DetailsActivityViewModel.KEY_ITEM, items.find { item ->
                            item.name == name
                        })
                    })
                }
            },
            viewBinding.activityHomeLoadingProgressBar,
            null
        )
    }
}