package proto.android.starwarsreference.core.category

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import bogdandonduk.commontoolboxlib.CommonToolbox
import proto.android.starwarsreference.R
import proto.android.starwarsreference.StarWarsReference

object CategoryManager {
    private const val KEY_LAST_OPEN_CATEGORY = "last_open_category"

    enum class CATEGORIES(val categoryName: String, val icon: Drawable, val accentIcon: Drawable) {
        PLANETS("Planets", ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_people_24, null)!!, ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_people_accent_24, null)!!),
        STARSHIPS("Starships", ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_people_24, null)!!, ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_people_accent_24, null)!!),
        VEHICLES("Vehicles", ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_people_24, null)!!, ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_people_accent_24, null)!!),
        PEOPLE("People", ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_people_24, null)!!, ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_people_accent_24, null)!!),
        FILMS("Films", ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_people_24, null)!!, ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_people_accent_24, null)!!),
        SPECIES("Species", ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_people_24, null)!!, ResourcesCompat.getDrawable(StarWarsReference.instance.resources, R.drawable.ic_baseline_people_accent_24, null)!!)
    }

    fun getCategories() = mutableListOf<Category<*>>().apply {
        // Dagger2 place
        add(PlanetsCategory(CATEGORIES.PLANETS.categoryName, CATEGORIES.PLANETS.icon, CATEGORIES.PLANETS.accentIcon))
        add(StarshipsCategory(CATEGORIES.STARSHIPS.categoryName, CATEGORIES.STARSHIPS.icon, CATEGORIES.STARSHIPS.accentIcon))
        add(VehiclesCategory(CATEGORIES.VEHICLES.categoryName, CATEGORIES.VEHICLES.icon, CATEGORIES.VEHICLES.accentIcon))
        add(PeopleCategory(CATEGORIES.PEOPLE.categoryName, CATEGORIES.PEOPLE.icon, CATEGORIES.PEOPLE.accentIcon))
        add(FilmsCategory(CATEGORIES.FILMS.categoryName, CATEGORIES.FILMS.icon, CATEGORIES.FILMS.accentIcon))
        add(SpeciesCategory(CATEGORIES.SPECIES.categoryName, CATEGORIES.SPECIES.icon, CATEGORIES.SPECIES.accentIcon))
    }.toList()

    fun getLastOpenCategoryName(context: Context) = CommonToolbox.getAppSharedPreferences(context).getString(KEY_LAST_OPEN_CATEGORY, CATEGORIES.PLANETS.categoryName)!!

    fun setLastOpenCategoryName(context: Context, name: String) {
        CommonToolbox.getAppSharedPreferences(context).edit().putString(KEY_LAST_OPEN_CATEGORY, name).apply()
    }
}