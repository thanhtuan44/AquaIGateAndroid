package vn.aquavietnam.aquaiget.base

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import vn.aquavietnam.aquaiget.BR
import vn.aquavietnam.aquaiget.BR.*

/**
 * Created by ThanhTuan on 3/21/2018.
 */
class RecyclerViewConfig : BaseObservable() {
    @get:Bindable
    var layoutManager: RecyclerView.LayoutManager? = null
        set(layoutManager) {
            field = layoutManager
            notifyPropertyChanged(BR.layoutManager)
        }
    @get:Bindable
    var itemAnimator: RecyclerView.ItemAnimator? = null
        set(itemAnimator) {
            field = itemAnimator
            notifyPropertyChanged(BR.itemAnimator)
        }
    @set:Bindable
    var itemDecoration: RecyclerView.ItemDecoration? = null
    @get:Bindable
    var adapter: RecyclerView.Adapter<*>? = null
        set(adapter) {
            field = adapter
            notifyPropertyChanged(BR.adapter)
        }

    companion object {
        @JvmStatic
        @BindingAdapter("app:configuration")
        fun configureRecyclerView(recyclerView: RecyclerView, configuration: RecyclerViewConfig) {
            recyclerView.layoutManager = configuration.layoutManager
            recyclerView.itemAnimator = configuration.itemAnimator
            recyclerView.addItemDecoration(configuration.itemDecoration)
            recyclerView.adapter = configuration.adapter
        }
    }
}