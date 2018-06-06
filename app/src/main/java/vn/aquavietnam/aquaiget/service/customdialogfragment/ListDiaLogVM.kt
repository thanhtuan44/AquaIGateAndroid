package vn.aquavietnam.aquaiget.service.customdialogfragment

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.model.DataBase
import vn.aquavietnam.aquaiget.model.ManagerInfo
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/10/2018.
 */
class ListDiaLogVM(context: Context?, listProduct: List<DataBase>?,id: String?): BaseVM()  {
    var dialogAdapter: DiaLogAdapter? = null
    var dialogAdapterManager: DiaLogAdapterManager? = null
    init {
        this.context = context
        if(id == null) {
            dialogAdapter = DiaLogAdapter(context!!,listProduct)
            initRecycler(dialogAdapter!!,1)
        }else {
            dialogAdapterManager = DiaLogAdapterManager(context!!,null)
            initRecycler(dialogAdapterManager!!,1)
        }
    }

    fun setAdapter(listProduct: List<DataBase>?) {
        dialogAdapter!!.arrData = listProduct
        dialogAdapter!!.notifyDataSetChanged()

    }
    fun setAdapterManager(listProduct: List<ManagerInfo>?) {
        dialogAdapterManager!!.arrData = listProduct
        dialogAdapterManager!!.notifyDataSetChanged()

    }
    fun getListManager(idManager: String) {
        AquaService.getListManagerService().setIdManage(idManager).execute().subscribe({ workDateDetail ->
            if (workDateDetail != null) {
                Log.v("ListDialogFragment", "3213")
                val dataBus = DataBus(DataBus.GET_LIST_MANAGER, workDateDetail)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("ListDialogFragmentError", error.message)
        })
    }
}