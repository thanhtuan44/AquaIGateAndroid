package vn.aquavietnam.aquaiget.homepage.listproducts

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.ListProductsRowBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.ListProducts

class ListProductsAdapter(var context: Context, private  var arrProductInfo: List<ListProducts>?,var product: Int): BaseAdapter() {
    lateinit var listProductsRowBinding: ListProductsRowBinding
    public var arrData: List<ListProducts>? = arrProductInfo
    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }

    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        listProductsRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_list_products, parent, false)
        return BindingViewHolder(listProductsRowBinding)
    }

    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        var listProductsRowBinding = holder.getBindingViewHolder() as ListProductsRowBinding
        listProductsRowBinding.listProducts = arrData!![position]
        holder.itemView.setBackgroundColor(Color.WHITE);
        listProductsRowBinding.cstLayoutBase.setOnClickListener {
            rowSelectMultiParameters?.invoke(arrData!![position].urlDetail,arrData!![position].urlParent,product)
        }
    }
}