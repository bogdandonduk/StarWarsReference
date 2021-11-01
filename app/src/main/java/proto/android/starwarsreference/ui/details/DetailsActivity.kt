package proto.android.starwarsreference.ui.details

import android.os.Bundle
import proto.android.starwarsreference.core.BaseActivity
import proto.android.starwarsreference.databinding.ActivityDetailsBinding

class DetailsActivity : BaseActivity<ActivityDetailsBinding, DetailsActivityViewModel>(
    {
        ActivityDetailsBinding.inflate(it)
    },
    {
        DetailsActivityViewModel(it.intent)
    }
) {
    override var slidable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getInitializedAppBar(this, viewBinding.activityDetailsToolbar)
            .modifyAsActionBar {
                getInitializedViewModel(this).item?.name?.run {
                    title = this
                }
            }
    }
}