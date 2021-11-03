package proto.android.starwarsreference.ui.home

import android.content.Context
import android.view.View
import proto.android.starwarsreference.core.BaseRecyclerViewAdapter
import proto.android.starwarsreference.core.item.Item
import proto.android.starwarsreference.databinding.LayoutItemBinding

class HomeContentAdapter(
    context: Context,
    items: List<Item>?,
    helper: BaseHelper?,
    loadingInProgressIndicator: View? = null,
    noItemsIndicator: View? = null,
) : BaseRecyclerViewAdapter<Item, BaseRecyclerViewAdapter.BaseViewHolder<Item, LayoutItemBinding>, BaseRecyclerViewAdapter.BaseHelper>(
    context,
    items,
    helper,
    loadingInProgressIndicator,
    noItemsIndicator,
    { layoutInflater, viewGroup, adapter ->
        BaseViewHolder(LayoutItemBinding.inflate(layoutInflater, viewGroup, false), adapter)
    }
) {
    override fun onBindViewHolder(holder: BaseViewHolder<Item, LayoutItemBinding>, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.run {
            viewBinding.layoutPlanetItemNameTextView.text = item.name
        }
    }
}