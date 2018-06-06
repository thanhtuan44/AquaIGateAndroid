package vn.aquavietnam.aquaiget.homepage

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.CategoryRowBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.Category


/**
 * Created by ThanhTuan on 3/20/2018.
 */
class HomePageAdapter(var context: Context, private  var arrCategoryHomepage: List<Category>) : BaseAdapter() {

    lateinit var categoryRowBinding: CategoryRowBinding
    override fun itemCount(): Int {
        return if (arrCategoryHomepage != null) {
            arrCategoryHomepage!!.size
        } else {
            0
        }
    }

    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        categoryRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_category, parent, false)
        return  BindingViewHolder(categoryRowBinding)
    }

    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        var categoryRowBinding = holder.getBindingViewHolder() as CategoryRowBinding
        categoryRowBinding.category = arrCategoryHomepage!![position]
        categoryRowBinding.cstLayoutBase.setOnClickListener {
            rowSelectOneParameter?.invoke(position)
        }
    }
}

