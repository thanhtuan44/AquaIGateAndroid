package vn.aquavietnam.aquaiget.profile.confirmovertime

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.model.MissingNonScan
import vn.aquavietnam.aquaiget.model.Overtime
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.profile.confirmnonscan.ConfirmNonScanAdapter
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus


/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ConfirmOvertimeVM (context: Context?, listOvertime: ArrayList<Overtime>?): BaseVM()   {
    var confirmOvertimeAdapter: ConfirmOvertimeAdapter? = null
    init {
        this.context = context
        confirmOvertimeAdapter = ConfirmOvertimeAdapter(context!!, listOvertime)
        initRecycler(confirmOvertimeAdapter!!,1)
    }
    fun setAdapter(listOvertime: ArrayList<Overtime>?) {
        confirmOvertimeAdapter!!.arrData = listOvertime
        confirmOvertimeAdapter!!.notifyDataSetChanged()

    }
    fun getListConfirmOvertime() {
        AquaService.getConfirmOvertime().execute().subscribe({ listOvertime ->
            if (listOvertime != null) {
                val dataBus = DataBus(DataBus.GET_LIST_CONFIRM_OVERTIME, listOvertime)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("WorkDateFragment", error.message)
        })
    }
}