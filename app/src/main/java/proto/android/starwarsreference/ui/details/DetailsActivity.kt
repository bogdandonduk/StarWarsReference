package proto.android.starwarsreference.ui.details

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.TextView
import proto.android.starwarsreference.R
import proto.android.starwarsreference.core.BaseActivity
import proto.android.starwarsreference.core.item.*
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

        printItemDetails()
    }

    private fun printItemDetails() {
        when(val item = getInitializedViewModel(this).item) {
            is Planet -> {
                viewBinding.activityDetailsContentsTextView.run {
                    text = SpannableStringBuilder(text).apply {
                        append(
                            String.format(getString(R.string.terrain_template), getString(R.string.rotation_period), item.rotationPeriod) + "\n" +
                            String.format(getString(R.string.terrain_template), getString(R.string.orbital_period), item.orbitalPeriod) + "\n" +
                            String.format(getString(R.string.terrain_template), getString(R.string.diameter), item.diameter) + "\n" +
                            String.format(getString(R.string.terrain_template), getString(R.string.climate), item.climate) + "\n" +
                            String.format(getString(R.string.terrain_template), getString(R.string.gravity), item.gravity) + "\n" +
                            String.format(getString(R.string.terrain_template), getString(R.string.terrain), item.terrain) + "\n" +
                            String.format(getString(R.string.terrain_template), getString(R.string.surfaceWater), item.surfaceWater) + "\n" +
                            String.format(getString(R.string.terrain_template), getString(R.string.population), item.population) + "\n" +
                            String.format(getString(R.string.terrain_template), getString(R.string.url), item.url)
                        )
                    }
                }
            }
            is Starship -> {

            }
            is Vehicle -> {

            }
            is Person -> {

            }
            is Film -> {

            }
            is Species -> {

            }

        }
    }
}