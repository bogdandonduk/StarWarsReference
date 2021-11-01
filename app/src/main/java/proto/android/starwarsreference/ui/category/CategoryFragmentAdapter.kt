package proto.android.starwarsreference.ui.category

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import proto.android.starwarsreference.core.BaseRecyclerViewAdapter
import proto.android.starwarsreference.core.item.Item
import proto.android.starwarsreference.databinding.LayoutItemBinding

class CategoryFragmentAdapter(
    context: Context,
    items: List<Item>,
    helper: BaseHelper,
) : BaseRecyclerViewAdapter<Item, CategoryFragmentAdapter.ViewHolder, BaseRecyclerViewAdapter.BaseHelper>(
    context,
    items,
    helper,
    { layoutInflater: LayoutInflater, viewGroup: ViewGroup ->
        ViewHolder(LayoutItemBinding.inflate(layoutInflater, viewGroup, false), helper)
    }
) {
    class ViewHolder(viewBinding: LayoutItemBinding, helper: BaseHelper) : BaseRecyclerViewAdapter.BaseViewHolder<Item, LayoutItemBinding>(viewBinding, helper) {
        override lateinit var item: Item
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.run {
            viewBinding.layoutPlanetItemNameTextView.text = item.name
        }
    }
}