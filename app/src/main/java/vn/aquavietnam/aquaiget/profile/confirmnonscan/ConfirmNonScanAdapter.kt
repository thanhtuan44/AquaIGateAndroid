package vn.aquavietnam.aquaiget.profile.confirmnonscan

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.ConfirmNonScanRowBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.MissingNonScan

/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ConfirmNonScanAdapter(var context: Context, private  var arrNonScan: List<MissingNonScan>?) : BaseAdapter() {
    lateinit var confirmNonScanRowBinding: ConfirmNonScanRowBinding
    public var arrData: List<MissingNonScan>? = arrNonScan

    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }

    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        confirmNonScanRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_list_confirm_nonscan, parent, false)
        return  BindingViewHolder(confirmNonScanRowBinding)
    }

    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        var listConfirmNonScanRowBinding = holder.getBindingViewHolder() as ConfirmNonScanRowBinding
        listConfirmNonScanRowBinding.nonscan = arrData!![position]
    }
}