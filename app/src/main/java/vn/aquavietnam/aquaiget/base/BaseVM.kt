package vn.aquavietnam.aquaiget.base

import android.content.Context
import android.databinding.BaseObservable
import android.graphics.Rect
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by ThanhTuan on 3/21/2018.
 */
open class BaseVM : BaseObservable() {
    var recyclerConfiguration = RecyclerViewConfig()
    var layout: GridLayoutManager? = null
    var context: Context? = null
    fun initRecycler(baseAdapter: BaseAdapter, numberSpan: Int) {
        layout = GridLayoutManager(context,numberSpan)
        recyclerConfiguration.layoutManager = layout
        recyclerConfiguration.itemAnimator = DefaultItemAnimator()
        recyclerConfiguration.itemDecoration = object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                super.getItemOffsets(outRect, view, parent, state)
            }
        }
        recyclerConfiguration.adapter = baseAdapter
    }
}