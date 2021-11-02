package proto.android.starwarsreference.ui.details

import android.os.Bundle
import android.text.SpannableStringBuilder
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
                viewBinding.activityDetailsContentsTextView.run {
                    text = SpannableStringBuilder(text).apply {
                        append(
                            String.format(getString(R.string.terrain_template), getString(R.string.model), item.model) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.manufacturer), item.manufacturer) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.cost_in_credits), item.costInCredits) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.length), item.length) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.max_atmosphering_speed), item.maxAtmospheringSpeed) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.crew), item.crew) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.passengers), item.passengers) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.cargo_capacity), item.cargoCapacity) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.consumables), item.consumables) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.hyperdrive_rating), item.hyperdriveRating) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.MGLT), item.MGLT) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.starship_class), item.`class`) + "\n" +

                                    String.format(getString(R.string.terrain_template), getString(R.string.url), item.url)
                        )
                    }
                }
            }
            is Vehicle -> {
                viewBinding.activityDetailsContentsTextView.run {
                    text = SpannableStringBuilder(text).apply {
                        append(
                            String.format(getString(R.string.terrain_template), getString(R.string.model), item.model) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.manufacturer), item.manufacturer) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.cost_in_credits), item.costInCredits) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.length), item.length) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.max_atmosphering_speed), item.maxAtmospheringSpeed) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.crew), item.crew) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.passengers), item.passengers) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.cargo_capacity), item.cargoCapacity) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.consumables), item.consumables) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.vehicle_class), item.`class`) + "\n" +

                                    String.format(getString(R.string.terrain_template), getString(R.string.url), item.url)
                        )
                    }
                }
            }
            is Person -> {
                viewBinding.activityDetailsContentsTextView.run {
                    text = SpannableStringBuilder(text).apply {
                        append(
                            String.format(getString(R.string.terrain_template), getString(R.string.rotation_period), item.height) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.orbital_period), item.mass) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.diameter), item.hairColor) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.climate), item.skinColor) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.gravity), item.eyeColor) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.terrain), item.birthYear) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.surfaceWater), item.gender) + "\n" +

                                    String.format(getString(R.string.terrain_template), getString(R.string.url), item.url)
                        )
                    }
                }
            }
            is Film -> {
                viewBinding.activityDetailsContentsTextView.run {
                    text = SpannableStringBuilder(text).apply {
                        append(
                            String.format(getString(R.string.terrain_template), getString(R.string.rotation_period), item.episodeId) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.orbital_period), item.openingCrawl) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.diameter), item.director) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.climate), item.producer) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.gravity), item.releaseDate) + "\n" +

                                    String.format(getString(R.string.terrain_template), getString(R.string.url), item.url)
                        )
                    }
                }
            }
            is Species -> {
                viewBinding.activityDetailsContentsTextView.run {
                    text = SpannableStringBuilder(text).apply {
                        append(
                            String.format(getString(R.string.terrain_template), getString(R.string.rotation_period), item.classification) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.orbital_period), item.designation) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.diameter), item.averageHeight) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.climate), item.skinColors) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.gravity), item.hairColors) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.terrain), item.eyeColors) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.surfaceWater), item.averageLifespan) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.surfaceWater), item.language) + "\n" +

                                    String.format(getString(R.string.terrain_template), getString(R.string.url), item.url)
                        )
                    }
                }
            }

        }
    }
}