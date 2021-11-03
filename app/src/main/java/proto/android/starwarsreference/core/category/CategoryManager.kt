package proto.android.starwarsreference.core.category

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import bogdandonduk.commontoolboxlib.CommonToolbox
import proto.android.starwarsreference.R
import proto.android.starwarsreference.StarWarsReference
import proto.android.starwarsreference.core.api.StarWarsAPI
import proto.android.starwarsreference.core.item.Item
import proto.android.starwarsreference.core.repo.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object CategoryManager {
    private const val KEY_LAST_OPEN_CATEGORY = "last_open_category"

    enum class CATEGORIES(val categoryName: String, val icon: Drawable, val accentIcon: Drawable) {
        PLANETS("Planets", ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_public_24, null)!!, ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_public_accent_24, null)!!),
        STARSHIPS("Starships", ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_airplanemode_active_24, null)!!, ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_airplanemode_active_accent_24, null)!!),
        VEHICLES("Vehicles", ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_directions_car_24, null)!!, ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_directions_car_accent_24, null)!!),
        PEOPLE("People", ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_emoji_people_24, null)!!, ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_emoji_people_accent_24, null)!!),
        FILMS("Films", ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_local_movies_24, null)!!, ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_local_movies_accent_24, null)!!),
        SPECIES("Species", ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_people_24, null)!!, ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_people_accent_24, null)!!)
    }

    fun getCategories() = mutableListOf<Category<out Item>>().apply {
        // Dagger2 place
        add(PlanetsCategory(CATEGORIES.PLANETS.categoryName, CATEGORIES.PLANETS.icon, CATEGORIES.PLANETS.accentIcon))
        add(StarshipsCategory(CATEGORIES.STARSHIPS.categoryName, CATEGORIES.STARSHIPS.icon, CATEGORIES.STARSHIPS.accentIcon))
        add(VehiclesCategory(CATEGORIES.VEHICLES.categoryName, CATEGORIES.VEHICLES.icon, CATEGORIES.VEHICLES.accentIcon))
        add(PeopleCategory(CATEGORIES.PEOPLE.categoryName, CATEGORIES.PEOPLE.icon, CATEGORIES.PEOPLE.accentIcon))
        add(FilmsCategory(CATEGORIES.FILMS.categoryName, CATEGORIES.FILMS.icon, CATEGORIES.FILMS.accentIcon))
        add(SpeciesCategory(CATEGORIES.SPECIES.categoryName, CATEGORIES.SPECIES.icon, CATEGORIES.SPECIES.accentIcon))
    }.toList()

    fun getLastOpenCategoryName(context: Context) = CommonToolbox.getAppSharedPreferences(context).getString(KEY_LAST_OPEN_CATEGORY, CATEGORIES.PEOPLE.categoryName)!!

    fun setLastOpenCategoryName(context: Context, name: String) {
        CommonToolbox.getAppSharedPreferences(context).edit().putString(KEY_LAST_OPEN_CATEGORY, name).apply()
    }

    fun getRepoForCategory(categoryName: String = getLastOpenCategoryName(StarWarsReference.instance)) : Repo<out Item> = when(categoryName) {
        CATEGORIES.PLANETS.categoryName -> PlanetsRepo.getSingleton(
            Retrofit.Builder().baseUrl(StarWarsAPI.BASE_URL).addCallAdapterFactory(
                RxJava2CallAdapterFactory.create()).build().create(StarWarsAPI::class.java))
        CATEGORIES.STARSHIPS.categoryName -> StarshipsRepo.getSingleton(
            Retrofit.Builder().baseUrl(
                StarWarsAPI.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(
                StarWarsAPI::class.java))
        CATEGORIES.VEHICLES.categoryName -> VehiclesRepo.getSingleton(
            Retrofit.Builder().baseUrl(
                StarWarsAPI.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(
                StarWarsAPI::class.java))
        CATEGORIES.FILMS.categoryName -> FilmsRepo.getSingleton(
            Retrofit.Builder().baseUrl(
                StarWarsAPI.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(
                StarWarsAPI::class.java))
        CATEGORIES.SPECIES.categoryName -> SpeciesRepo.getSingleton(
            Retrofit.Builder().baseUrl(
                StarWarsAPI.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(
                StarWarsAPI::class.java))

        else -> PeopleRepo.getSingleton(
            Retrofit.Builder().baseUrl(
                StarWarsAPI.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(
                StarWarsAPI::class.java))
    }
}