package vn.aquavietnam.aquaiget.base

import android.databinding.ViewDataBinding
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import java.util.*

/**
 * Created by ThanhTuan on 3/21/2018.
 */
abstract class BaseAdapter : RecyclerView.Adapter<BaseAdapter.BindingViewHolder>() {

    var rowSelectOneParameter: ((Int) -> Unit)? =  null
    var rowSelectMultiParameters: ((String, String, Int) -> Unit)? =  null
    var rowSelectParcelableParameters: ((Parcelable,Int) -> Unit)? =  null
    var rowSelectDataSwipe: ((Parcelable,Int,String) -> Unit)? =  null

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        if (holder != null) {
            bindBaseViewHolder(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return itemCount()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return createBaseViewHolder(parent!!, viewType)
    }
    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }
    inner class BindingViewHolder(viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        private var bindingViewBinding: ViewDataBinding = viewDataBinding

        init {
            bindingViewBinding.executePendingBindings()

        }
        fun getBindingViewHolder(): ViewDataBinding {
            return bindingViewBinding
        }

    }

    abstract fun bindBaseViewHolder(holder: BindingViewHolder, position: Int)
    abstract fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder
    abstract fun itemCount(): Int
}