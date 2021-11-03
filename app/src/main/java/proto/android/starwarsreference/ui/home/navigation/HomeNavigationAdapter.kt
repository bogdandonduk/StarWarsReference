package proto.android.starwarsreference.ui.home.navigation

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import proto.android.starwarsreference.R
import proto.android.starwarsreference.core.BaseRecyclerViewAdapter
import proto.android.starwarsreference.core.category.Category
import proto.android.starwarsreference.core.category.CategoryManager
import proto.android.starwarsreference.core.item.Item
import proto.android.starwarsreference.databinding.LayoutNavigationItemBinding

class HomeNavigationAdapter(
    context: Context,
    items: List<Category<out Item>>,
    helper: BaseHelper?,
) : BaseRecyclerViewAdapter<Category<out Item>, BaseRecyclerViewAdapter.BaseViewHolder<Category<out Item>, LayoutNavigationItemBinding>, BaseRecyclerViewAdapter.BaseHelper>(
    context,
    items,
    helper,
    null,
    null,
    { layoutInflater, viewGroup, adapter ->
        BaseViewHolder(LayoutNavigationItemBinding.inflate(layoutInflater, viewGroup, false), adapter)
    }
) {
    private var lastOpenCategoryName: String = CategoryManager.getLastOpenCategoryName(context)

    private val textColor = ResourcesCompat.getColor(context.resources, R.color.text, null)
    private val accentColor = ResourcesCompat.getColor(context.resources, R.color.accent, null)

    override fun onBindViewHolder(holder: BaseViewHolder<Category<out Item>, LayoutNavigationItemBinding>, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.run {
            viewBinding.layoutNavigationItemLabelTextView.text = item.name

            if(item.name != lastOpenCategoryName) {
                viewBinding.layoutNavigationItemLabelTextView.setTextColor(textColor)

                viewBinding.layoutNavigationItemIconImageView.setImageDrawable(item.icon)
            } else {
                viewBinding.layoutNavigationItemLabelTextView.setTextColor(accentColor)

                viewBinding.layoutNavigationItemIconImageView.setImageDrawable(item.accentIcon)
            }
        }
    }

    override fun rebind() {
        lastOpenCategoryName = CategoryManager.getLastOpenCategoryName(context)

        super.rebind()
    }
}