package vn.aquavietnam.aquaiget.service.customdialogfragment

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.DiaLogRowBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.DataBase
import vn.aquavietnam.aquaiget.model.ManagerInfo
import android.databinding.adapters.CompoundButtonBindingAdapter.setChecked




/**
 * Created by ThanhTuan on 5/10/2018.
 */
class DiaLogAdapter(var context: Context, listProduct: List<DataBase>?): BaseAdapter() {

    lateinit var dialogRowBinding: DiaLogRowBinding
    public var arrData: List<DataBase>? = listProduct
    var rowSelectDiaLog: ((DataBase,Int) -> Unit)? =  null
    var indexSelected : Int = -1

    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }
    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        dialogRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_dialog, parent, false)
        return  BindingViewHolder(dialogRowBinding)
    }



    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        var productRowBinding = holder.getBindingViewHolder() as DiaLogRowBinding
        productRowBinding.lblName.visibility = View.INVISIBLE
        productRowBinding.dataInfo = this.getItem(position) as DataBase
        if(position == indexSelected) {
            productRowBinding.imvSelect.setBackgroundResource(R.drawable.iconselected)
        }else {
            productRowBinding.imvSelect.setBackgroundResource(R.drawable.iconunselected)
        }
        productRowBinding.cstLayoutRowDiaLog.setOnClickListener {
            rowSelectDiaLog?.invoke(productRowBinding.dataInfo as DataBase,position)

        }

    }
    fun getItem(position: Int): Any {
        return arrData!!.get(position)
    }
}
class DiaLogAdapterManager(var context: Context, listProduct: List<ManagerInfo>?): BaseAdapter() {
    var indexSelected : Int = -1
    lateinit var managerRowBinding: DiaLogRowBinding
    var rowSelectDiaLogManager: ((ManagerInfo,Int) -> Unit)? =  null

    public var arrData: List<ManagerInfo>? = listProduct
    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }
    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        managerRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_dialog, parent, false)
        return  BindingViewHolder(managerRowBinding)
    }

    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        var managerRowBinding = holder.getBindingViewHolder() as DiaLogRowBinding
        managerRowBinding.lblInfo.visibility = View.INVISIBLE
        managerRowBinding.dataManagerInfo = arrData!![position]
        if(position == indexSelected) {
            managerRowBinding.imvSelect.setBackgroundResource(R.drawable.iconselected)
        }else {
            managerRowBinding.imvSelect.setBackgroundResource(R.drawable.iconunselected)
        }
        managerRowBinding.cstLayoutRowDiaLog.setOnClickListener {
            rowSelectDiaLogManager?.invoke(managerRowBinding.dataManagerInfo as ManagerInfo,position)
        }

    }
}