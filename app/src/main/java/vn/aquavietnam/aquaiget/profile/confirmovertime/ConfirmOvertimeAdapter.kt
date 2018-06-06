package vn.aquavietnam.aquaiget.profile.confirmovertime

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.ConfirmOvertimeRowBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.Overtime

/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ConfirmOvertimeAdapter(var context: Context, private  var arrOvertime: ArrayList<Overtime>?) : BaseAdapter() {
    lateinit var confirmOvertimeRowBinding: ConfirmOvertimeRowBinding
    public var arrData: ArrayList<Overtime>? = arrOvertime

    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }

    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        confirmOvertimeRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_list_confirm_overtime, parent, false)
        return  BindingViewHolder(confirmOvertimeRowBinding)
    }

    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        var listConfirmOvertimeRowBinding = holder.getBindingViewHolder() as ConfirmOvertimeRowBinding
        listConfirmOvertimeRowBinding.overtime = arrData!![position]
    }
}