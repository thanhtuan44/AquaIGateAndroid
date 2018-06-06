package vn.aquavietnam.aquaiget.profile.listregisterdateoff

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.ListRegisterDateOffRowBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.Absent

/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ListRegisterDateOffAdapter(var context: Context, private  var arrAbsent: List<Absent>?) : BaseAdapter() {

    lateinit var listRegisterDateOffRowBinding: ListRegisterDateOffRowBinding
    public var arrData: List<Absent>? = arrAbsent

    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }

    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        listRegisterDateOffRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_list_register_dateoff, parent, false)
        return  BindingViewHolder(listRegisterDateOffRowBinding)
    }

    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        var listRegisterDateOffRowBinding = holder.getBindingViewHolder() as ListRegisterDateOffRowBinding
        listRegisterDateOffRowBinding.absent = arrData!![position]
        when (listRegisterDateOffRowBinding.absent!!.status) {
            "Tạo mới" -> listRegisterDateOffRowBinding.imvStatus.setBackgroundResource(R.drawable.iconpending)
            "Chờ duyệt" -> listRegisterDateOffRowBinding.imvStatus.setBackgroundResource(R.drawable.iconpending)
            "Đã duyệt" -> listRegisterDateOffRowBinding.imvStatus.setBackgroundResource(R.drawable.iconsuccess)
            "Từ chối" -> listRegisterDateOffRowBinding.imvStatus.setBackgroundResource(R.drawable.icondenide)
            else -> {
                listRegisterDateOffRowBinding.imvStatus.setImageResource(R.drawable.iconpending)
            }
        }
    }
}