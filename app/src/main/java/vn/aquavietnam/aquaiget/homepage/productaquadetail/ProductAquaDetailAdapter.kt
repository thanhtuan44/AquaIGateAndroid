package vn.aquavietnam.aquaiget.homepage.productaquadetail

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.InfoDetailRowBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.InfoDetail

class ProductAquaDetailAdapter(var context: Context, private  var arrInfoDetail: List<InfoDetail>?): BaseAdapter() {
    lateinit var infoDetailRowBinding: InfoDetailRowBinding
    public var arrData: List<InfoDetail>? = arrInfoDetail
    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }

    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        infoDetailRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_product_aqua_detail, parent, false)
        return BindingViewHolder(infoDetailRowBinding)
    }

    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        var infoDetailRowBinding = holder.getBindingViewHolder() as InfoDetailRowBinding
        infoDetailRowBinding.infoDetail = arrData!![position]
        holder.itemView.setBackgroundColor(Color.WHITE);
    }
}