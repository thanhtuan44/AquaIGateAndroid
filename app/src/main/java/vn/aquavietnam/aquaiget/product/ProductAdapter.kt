package vn.aquavietnam.aquaiget.product

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.ProductRowBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.Product

class ProductAdapter(var context: Context, private  var arrProduct: List<Product>?): BaseAdapter() {
    var selectProduct: ((String) -> Unit)? =  null
    lateinit var productRowBinding: ProductRowBinding
    public var arrData: List<Product>? = arrProduct
    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }

    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        productRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_product, parent, false)
        return  BindingViewHolder(productRowBinding)
    }

    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        var productRowBinding = holder.getBindingViewHolder() as ProductRowBinding
        productRowBinding.product = arrData!![position]
        productRowBinding.cstLayoutBase.setOnClickListener {
            selectProduct?.invoke(productRowBinding.product!!.id!!)
        }
    }
}