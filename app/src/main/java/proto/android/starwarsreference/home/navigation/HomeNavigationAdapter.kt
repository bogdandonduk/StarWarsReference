package proto.android.starwarsreference.home.navigation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import proto.android.starwarsreference.R
import proto.android.starwarsreference.core.base.BaseRecyclerViewAdapter
import proto.android.starwarsreference.core.categories.CategoriesManager
import proto.android.starwarsreference.core.categories.Category
import proto.android.starwarsreference.databinding.LayoutNavigationItemBinding

class HomeNavigationAdapter(
    context: Context,
    items: List<Category>,
    helper: BaseHelper,
) : BaseRecyclerViewAdapter<Category, HomeNavigationAdapter.ViewHolder, BaseRecyclerViewAdapter.BaseHelper>(
    context,
    items,
    helper,
    { layoutInflater: LayoutInflater, viewGroup: ViewGroup ->
        ViewHolder(LayoutNavigationItemBinding.inflate(layoutInflater, viewGroup, false), helper)
    }
) {
    private var lastOpenCategoryId: Long = CategoriesManager.getLastOpenCategoryId(context)

    private val textColor = ResourcesCompat.getColor(context.resources, R.color.text, null)
    private val accentColor = ResourcesCompat.getColor(context.resources, R.color.accent, null)

    class ViewHolder(viewBinding: LayoutNavigationItemBinding, helper: BaseHelper) : BaseRecyclerViewAdapter.BaseViewHolder<Category, LayoutNavigationItemBinding>(viewBinding, helper) {
        override lateinit var item: Category
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.run {
            viewBinding.layoutNavigationItemLabelTextView.text = item.name

            if(item.intrinsicId != lastOpenCategoryId) {
                viewBinding.layoutNavigationItemLabelTextView.setTextColor(textColor)

                viewBinding.layoutNavigationItemIconImageView.setImageDrawable(item.icon)
            } else {
                viewBinding.layoutNavigationItemLabelTextView.setTextColor(accentColor)

                viewBinding.layoutNavigationItemIconImageView.setImageDrawable(item.accentIcon)
            }
        }
    }

    override fun rebind() {
        lastOpenCategoryId = CategoriesManager.getLastOpenCategoryId(context)

        super.rebind()
    }
}