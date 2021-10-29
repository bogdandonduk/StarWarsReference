package proto.android.starwarsreference.core.categories

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import bogdandonduk.commontoolboxlib.CommonToolbox
import proto.android.starwarsreference.R

object CategoriesManager {
    private const val KEY_LAST_OPEN_CATEGORY = "last_open_category"

    fun getCategories(context: Context) = arrayListOf<Category>().apply {
        add(Category(0, "Planets", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_people_24, null)!!, ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_people_accent_24, null)!!))
        add(Category(1, "Starships", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_people_24, null)!!, ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_people_accent_24, null)!!))
        add(Category(2, "Vehicles", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_people_24, null)!!, ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_people_accent_24, null)!!))
        add(Category(3, "People", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_people_24, null)!!, ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_people_accent_24, null)!!))
        add(Category(4, "Films", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_people_24, null)!!, ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_people_accent_24, null)!!))
        add(Category(5, "Species", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_people_24, null)!!, ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_people_accent_24, null)!!))
    }.toList()

    fun getLastOpenCategoryId(context: Context) = CommonToolbox.getAppSharedPreferences(context).getLong(KEY_LAST_OPEN_CATEGORY, 0)

    fun setLastOpenCategoryId(context: Context, id: Long) {
        CommonToolbox.getAppSharedPreferences(context).edit().putLong(KEY_LAST_OPEN_CATEGORY, id).apply()
    }
}