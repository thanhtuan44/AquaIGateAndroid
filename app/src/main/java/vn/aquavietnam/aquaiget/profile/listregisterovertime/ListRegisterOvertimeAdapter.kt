package vn.aquavietnam.aquaiget.profile.listregisterovertime

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.ListRegisterOvertimeRowBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.Overtime

/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ListRegisterOvertimeAdapter(var context: Context, private  var arrOvertime: List<Overtime>?) : BaseAdapter() {
    lateinit var listRegisterOvertimeRowBinding: ListRegisterOvertimeRowBinding
    public var arrData: List<Overtime>? = arrOvertime

    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }

    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BaseAdapter.BindingViewHolder {
        listRegisterOvertimeRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_list_register_overtime, parent, false)
        return  BindingViewHolder(listRegisterOvertimeRowBinding)
    }

    override fun bindBaseViewHolder(holder: BaseAdapter.BindingViewHolder, position: Int) {
        var listRegisterOvertimeRowBinding = holder.getBindingViewHolder() as ListRegisterOvertimeRowBinding
        listRegisterOvertimeRowBinding.overtime = arrData!![position]
        when (listRegisterOvertimeRowBinding.overtime!!.status) {
            "Tạo mới" -> listRegisterOvertimeRowBinding.imvStatus.setImageResource(R.drawable.iconpending)
            "Chờ duyệt" -> listRegisterOvertimeRowBinding.imvStatus.setImageResource(R.drawable.iconpending)
            "Đã duyệt" -> listRegisterOvertimeRowBinding.imvStatus.setImageResource(R.drawable.iconsuccess)
            "Từ chối" -> listRegisterOvertimeRowBinding.imvStatus.setImageResource(R.drawable.icondenide)
            else -> {
                listRegisterOvertimeRowBinding.imvStatus.setImageResource(R.drawable.iconpending)
            }
        }
    }
}