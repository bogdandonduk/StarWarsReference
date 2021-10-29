package proto.android.starwarsreference.core.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import bogdandonduk.viewdatabindingwrapperslib.ViewBindingHandler

abstract class BaseRecyclerViewAdapter<
        ItemType : BaseItem,
        ViewHolderType : BaseRecyclerViewAdapter.BaseViewHolder<ItemType, out ViewBinding>,
        HelperType : BaseRecyclerViewAdapter.BaseHelper>(
    val context: Context,
    var items: List<ItemType>,
    val helper: HelperType,
    private val viewHolderInitialization: (LayoutInflater, parent: ViewGroup) -> ViewHolderType,
) : RecyclerView.Adapter<ViewHolderType>() {
    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = viewHolderInitialization.invoke(layoutInflater, parent)

    override fun onBindViewHolder(holder: ViewHolderType, position: Int) {
        holder.item = items[position]
    }

    override fun getItemCount() = items.size

    open fun rebind(items: List<ItemType>) {
        this.items = items

        rebind()
    }

    open fun rebind() {
        for(i in 0 until itemCount)
            notifyItemChanged(i)
    }

    @Suppress("UNCHECKED_CAST")
    abstract class BaseViewHolder<ItemType : BaseItem, ViewBindingType : ViewBinding>(final override var viewBinding: ViewBindingType, open val helper: BaseHelper) : RecyclerView.ViewHolder(viewBinding.root), ViewBindingHandler<ViewBindingType> {
        abstract var item: ItemType

        init {
            viewBinding.root.run {
                setOnClickListener {
                    helper.onItemClicked(context, item.intrinsicId)
                }
            }
        }
    }

    interface BaseHelper {
        fun onItemClicked(context: Context, id: Long)
    }
}