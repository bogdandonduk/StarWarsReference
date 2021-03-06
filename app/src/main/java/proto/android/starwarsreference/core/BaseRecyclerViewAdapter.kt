package proto.android.starwarsreference.core

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import bogdandonduk.viewdatabindingwrapperslib.ViewBindingHandler
import proto.android.starwarsreference.core.item.Item

abstract class BaseRecyclerViewAdapter<
        ItemType : Item,
        ViewHolderType : BaseRecyclerViewAdapter.BaseViewHolder<ItemType, out ViewBinding>,
        HelperType : BaseRecyclerViewAdapter.BaseHelper>(
    val context: Context,
    items: List<ItemType>?,
    var helper: HelperType? = null,
    // loading indicators
    var loadingInProgressIndicator: View? = null,
    var noItemsIndicator: View? = null,
    private val viewHolderInitialization: (LayoutInflater, parent: ViewGroup, adapter: BaseRecyclerViewAdapter<ItemType, ViewHolderType, HelperType>) -> ViewHolderType,
) : ListAdapter<ItemType, ViewHolderType>(object : DiffUtil.ItemCallback<ItemType>() {
    override fun areItemsTheSame(oldItem: ItemType, newItem: ItemType) = newItem.name == oldItem.name

    override fun areContentsTheSame(oldItem: ItemType, newItem: ItemType) = newItem.toString() == oldItem.toString()
}) {
    private lateinit var recyclerView: RecyclerView

    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @Volatile var scrollToTopAfterUpdate = true

    init {
        submitItems(items, true)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = viewHolderInitialization.invoke(layoutInflater, parent, this)

    override fun onBindViewHolder(holder: ViewHolderType, position: Int) {
        holder.item = currentList[position]
    }

    override fun getItemCount() = currentList.size

    fun setIndicatorViews(loadingInProgressIndicator: View?, noItemsIndicator: View?) {
        this.loadingInProgressIndicator = loadingInProgressIndicator
        this.noItemsIndicator = noItemsIndicator
    }

    private fun postConfigureIndicators(newItemsCount: Int) {
        if(newItemsCount > 0) {
            if(this::recyclerView.isInitialized)
                recyclerView.visibility = View.VISIBLE

            loadingInProgressIndicator?.visibility = View.GONE
            noItemsIndicator?.visibility = View.GONE
        } else {
            if(this::recyclerView.isInitialized)
                recyclerView.visibility = View.GONE

            loadingInProgressIndicator?.visibility = View.GONE
            noItemsIndicator?.visibility = View.VISIBLE
        }
    }

    private fun preConfigureIndicators() {
        noItemsIndicator?.visibility = View.GONE
        loadingInProgressIndicator?.visibility = View.VISIBLE
    }

    open fun rebind() {
        for(i in 0 until itemCount)
            notifyItemChanged(i)
    }

    fun submitItems(items: List<ItemType>?, rebindAll: Boolean = false) {
        preConfigureIndicators()

        submitList(items) {
            if(this::recyclerView.isInitialized)
                if(scrollToTopAfterUpdate)
                    recyclerView.scrollToPosition(0)

            if(rebindAll)
                rebind()

            postConfigureIndicators(items?.size ?: 0)
        }
    }

    class BaseViewHolder<ItemType : Item, ViewBindingType : ViewBinding>(override var viewBinding: ViewBindingType, val adapter: BaseRecyclerViewAdapter<ItemType, out BaseViewHolder<ItemType, ViewBindingType>, out BaseHelper>) : RecyclerView.ViewHolder(viewBinding.root), ViewBindingHandler<ViewBindingType> {
        lateinit var item: ItemType

        init {
            viewBinding.root.run {
                setOnClickListener {
                    adapter.helper?.onItemClicked(context, item.name.toString())
                }
            }
        }
    }

    interface BaseHelper {
        fun onItemClicked(context: Context, name: String)
    }

    fun findItemByName(name: String) = currentList.find {
        it.name == name
    }
}