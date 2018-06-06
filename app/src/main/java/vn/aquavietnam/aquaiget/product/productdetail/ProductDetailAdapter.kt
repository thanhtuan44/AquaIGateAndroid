package vn.aquavietnam.aquaiget.product.productdetail

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.CategoryRowBinding
import vn.aquavietnam.aquaiget.ProductDetailRowBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.Product
import vn.aquavietnam.aquaiget.model.ProductDetail
import vn.aquavietnam.aquaiget.model.ProductDetailFeature

/**
 * Created by ThanhTuan on 5/25/2018.
 */
class ProductDetailAdapter(var context: Context, private  var arrProduct: List<ProductDetailFeature>?) : BaseAdapter()  {

    public var arrData: List<ProductDetailFeature>? = arrProduct

    lateinit var productDetailRowBinding: ProductDetailRowBinding
    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }

    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        productDetailRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_product_detail, parent, false)
        return  BindingViewHolder(productDetailRowBinding)
    }

    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        var productDetailRowBinding = holder.getBindingViewHolder() as ProductDetailRowBinding
        productDetailRowBinding.productDetail = arrData!![position]
    }
}