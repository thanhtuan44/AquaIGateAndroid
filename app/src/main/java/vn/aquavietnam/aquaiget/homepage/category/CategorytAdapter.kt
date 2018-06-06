package vn.aquavietnam.aquaiget.homepage.category

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.model.CategoryInfo
import vn.aquavietnam.aquaiget.CategoryProductRowBinding
import vn.aquavietnam.aquaiget.CategoryProductSpecRowBinding
import vn.aquavietnam.aquaiget.model.Product

class CategorytAdapter(var context: Context, private  var arrProductInfo: List<CategoryInfo>, private var product: Int) : BaseAdapter() {

    lateinit var categoryProductRowBinding: CategoryProductRowBinding
    lateinit var categoryProductSpecRowBinding: CategoryProductSpecRowBinding

    override fun itemCount(): Int {
        return if (arrProductInfo != null) {
            arrProductInfo!!.size
        } else {
            0
        }
    }
    override fun getItemViewType(position: Int): Int {
        return position % 2 * 2
    }
    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        if(viewType == 2) {
            categoryProductRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_category_product, parent, false)
            return BindingViewHolder(categoryProductRowBinding)
        }else{
            categoryProductSpecRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_category_product_spec, parent, false)
            return BindingViewHolder(categoryProductSpecRowBinding)
        }
    }

    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        if(position % 2 == 1) {
            var categoryProductRowBinding = holder.getBindingViewHolder() as CategoryProductRowBinding
            categoryProductRowBinding.categoryInfo = arrProductInfo!![position]
            categoryProductRowBinding.cstLayoutBase.setOnClickListener {
                rowSelectMultiParameters?.invoke("",arrProductInfo!![position].url,product)
            }
        }else{
            var categoryProductSpecRowBinding = holder.getBindingViewHolder() as CategoryProductSpecRowBinding
            categoryProductSpecRowBinding.categoryInfoSpec = arrProductInfo!![position]
            categoryProductSpecRowBinding.cstLayoutBase.setOnClickListener {
                rowSelectMultiParameters?.invoke("",arrProductInfo!![position].url,product)
            }
        }
        holder.itemView.setBackgroundColor(Color.WHITE);

    }
}