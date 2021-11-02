package proto.android.starwarsreference.ui.home.navigation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import proto.android.starwarsreference.R
import proto.android.starwarsreference.core.BaseRecyclerViewAdapter
import proto.android.starwarsreference.core.category.Category
import proto.android.starwarsreference.core.category.CategoryManager
import proto.android.starwarsreference.core.item.Item
import proto.android.starwarsreference.databinding.LayoutNavigationItemBinding

class HomeNavigationAdapter(
    context: Context,
    items: List<Category<*>>,
    helper: BaseHelper,
) : BaseRecyclerViewAdapter<HomeNavigationAdapter.ViewHolder, BaseRecyclerViewAdapter.BaseHelper>(
    context,
    items,
    helper,
    null,
    null,
    { layoutInflater: LayoutInflater, viewGroup: ViewGroup ->
        ViewHolder(LayoutNavigationItemBinding.inflate(layoutInflater, viewGroup, false), helper)
    }
) {
    private var lastOpenCategoryName: String = CategoryManager.getLastOpenCategoryName(context)

    private val textColor = ResourcesCompat.getColor(context.resources, R.color.text, null)
    private val accentColor = ResourcesCompat.getColor(context.resources, R.color.accent, null)

    class ViewHolder(viewBinding: LayoutNavigationItemBinding, helper: BaseHelper) : BaseRecyclerViewAdapter.BaseViewHolder<Item, LayoutNavigationItemBinding>(viewBinding, helper) {
        override lateinit var item: Item
        fun getCastItem() = item as Category<*>
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.run {
            viewBinding.layoutNavigationItemLabelTextView.text = item.name

            if(item.name != lastOpenCategoryName) {
                viewBinding.layoutNavigationItemLabelTextView.setTextColor(textColor)

                viewBinding.layoutNavigationItemIconImageView.setImageDrawable(getCastItem().icon)
            } else {
                viewBinding.layoutNavigationItemLabelTextView.setTextColor(accentColor)

                viewBinding.layoutNavigationItemIconImageView.setImageDrawable(getCastItem().accentIcon)
            }
        }
    }

    override fun rebind() {
        lastOpenCategoryName = CategoryManager.getLastOpenCategoryName(context)

        super.rebind()
    }
}