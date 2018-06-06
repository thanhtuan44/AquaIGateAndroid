package vn.aquavietnam.aquaiget.profile.listregisternonscan

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.ListRegisterNonScanRowBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.MissingNonScan

/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ListRegisterNonScanAdapter(var context: Context, private  var arrNonScan: List<MissingNonScan>?) : BaseAdapter() {
    lateinit var listRegisterNonScanRowBinding: ListRegisterNonScanRowBinding
    public var arrData: List<MissingNonScan>? = arrNonScan

    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }

    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        listRegisterNonScanRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_list_register_nonscan, parent, false)
        return  BindingViewHolder(listRegisterNonScanRowBinding)
    }

    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        var listRegisterNonScanRowBinding = holder.getBindingViewHolder() as ListRegisterNonScanRowBinding
        listRegisterNonScanRowBinding.nonscan = arrData!![position]
        when (listRegisterNonScanRowBinding.nonscan!!.statusName) {
            "Tạo mới" -> listRegisterNonScanRowBinding.imvStatus.setBackgroundResource(R.drawable.iconpending)
            "Chờ duyệt" -> listRegisterNonScanRowBinding.imvStatus.setBackgroundResource(R.drawable.iconpending)
            "Đã duyệt" -> listRegisterNonScanRowBinding.imvStatus.setBackgroundResource(R.drawable.iconsuccess)
            "Từ chối" -> listRegisterNonScanRowBinding.imvStatus.setBackgroundResource(R.drawable.icondenide)
            else -> {
                listRegisterNonScanRowBinding.imvStatus.setImageResource(R.drawable.iconpending)
            }
        }
    }
}