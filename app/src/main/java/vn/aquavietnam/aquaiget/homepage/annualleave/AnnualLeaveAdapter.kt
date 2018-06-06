package vn.aquavietnam.aquaiget.homepage.annualleave

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.AnnualLeaveRowBinding
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.AnnualLeaveInfo

class AnnualLeaveAdapter(var context: Context, private  var arrAnnualLeave: List<AnnualLeaveInfo>?) : BaseAdapter() {
    lateinit var annualLeaveRowBinding: AnnualLeaveRowBinding
    public var arrData: List<AnnualLeaveInfo>? = arrAnnualLeave
    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }
    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        annualLeaveRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_annualleave, parent, false)
        return  BindingViewHolder(annualLeaveRowBinding)
    }
    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        var annualLeaveRowBinding = holder.getBindingViewHolder() as AnnualLeaveRowBinding
        annualLeaveRowBinding.annualLeaveInfo = arrData!![position]
    }
}