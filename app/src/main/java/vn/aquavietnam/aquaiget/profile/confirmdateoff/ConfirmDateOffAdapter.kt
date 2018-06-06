package vn.aquavietnam.aquaiget.profile.confirmdateoff

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.ConfirmDateOffRowBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.Absent

/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ConfirmDateOffAdapter(var context: Context, private  var arrAbsent: ArrayList<Absent>?) : BaseAdapter() {
    lateinit var confirmDateOffRowBinding: ConfirmDateOffRowBinding
    public var arrData: ArrayList<Absent>? = arrAbsent

    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }

    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        confirmDateOffRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_list_confirm_dateoff, parent, false)
        return  BindingViewHolder(confirmDateOffRowBinding)
    }

    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        var listConfirmDateOffRowBinding = holder.getBindingViewHolder() as ConfirmDateOffRowBinding
        listConfirmDateOffRowBinding.absent = arrData!![position]
        listConfirmDateOffRowBinding.btnConfirmSuccess.setOnClickListener {
            rowSelectOneParameter?.invoke(position)
        }
    }
}