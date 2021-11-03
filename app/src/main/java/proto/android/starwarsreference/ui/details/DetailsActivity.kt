package proto.android.starwarsreference.ui.details

import android.app.Activity
import android.os.Bundle
import android.text.SpannableStringBuilder
import bogdandonduk.viewmodelwrapperslib.automatic.SingleAutomaticInitializationWithInitializationViewModelHandlerActivity
import proto.android.starwarsreference.R
import proto.android.starwarsreference.core.BaseActivity
import proto.android.starwarsreference.core.item.*
import proto.android.starwarsreference.databinding.ActivityDetailsBinding

class DetailsActivity : BaseActivity<ActivityDetailsBinding, DetailsActivityViewModel>(
    {
        ActivityDetailsBinding.inflate(it)
    }
), SingleAutomaticInitializationWithInitializationViewModelHandlerActivity<DetailsActivityViewModel> {
    override var viewModelInitialization: (activity: Activity) -> DetailsActivityViewModel = {
        DetailsActivityViewModel(it.intent)
    }

    override var slidable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getInitializedAppBar(this, viewBinding.activityDetailsToolbar)
            .modifyAsActionBar {
                getInitializedViewModel(this).item!!.name.run {
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
                            String.format(getString(R.string.terrain_template), getString(R.string.height), item.height) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.mass), item.mass) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.hair_color), item.hairColor) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.skin_color), item.skinColor) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.eye_color), item.eyeColor) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.birth_year), item.birthYear) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.gender), item.gender) + "\n" +

                                    String.format(getString(R.string.terrain_template), getString(R.string.url), item.url)
                        )
                    }
                }
            }
            is Film -> {
                viewBinding.activityDetailsContentsTextView.run {
                    text = SpannableStringBuilder(text).apply {
                        append(
                            String.format(getString(R.string.terrain_template), getString(R.string.episode_id), item.episodeId) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.opening_crawl), item.openingCrawl) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.director), item.director) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.producer), item.producer) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.release_date), item.releaseDate) + "\n" +

                                    String.format(getString(R.string.terrain_template), getString(R.string.url), item.url)
                        )
                    }
                }
            }
            is Species -> {
                viewBinding.activityDetailsContentsTextView.run {
                    text = SpannableStringBuilder(text).apply {
                        append(
                            String.format(getString(R.string.terrain_template), getString(R.string.classification), item.classification) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.designation), item.designation) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.average_height), item.averageHeight) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.skin_colors), item.skinColors) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.hair_colors), item.hairColors) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.eye_colors), item.eyeColors) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.average_lifespan), item.averageLifespan) + "\n" +
                                    String.format(getString(R.string.terrain_template), getString(R.string.language), item.language) + "\n" +

                                    String.format(getString(R.string.terrain_template), getString(R.string.url), item.url)
                        )
                    }
                }
            }

        }
    }
}