package vn.aquavietnam.aquaiget.profile.listregisterovertime

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.model.Absent
import vn.aquavietnam.aquaiget.model.Overtime
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ListRegisterOvertimeVM(context: Context?, listOvertime: List<Overtime>?): BaseVM() {
    var listRegisterOvertimeAdapter: ListRegisterOvertimeAdapter? = null
    init {
        this.context = context
        listRegisterOvertimeAdapter = ListRegisterOvertimeAdapter(context!!, listOvertime)
        initRecycler(listRegisterOvertimeAdapter!!,1)
    }
    fun setAdapter(listOvertime: List<Overtime>?) {
        listRegisterOvertimeAdapter!!.arrData = listOvertime
        listRegisterOvertimeAdapter!!.notifyDataSetChanged()

    }
    fun getListRegisterOvertime() {
        AquaService.getListRegisterOvertime().execute().subscribe({ listAbsent ->
            if (listAbsent != null) {
                val dataBus = DataBus(DataBus.GET_LIST_REGISTER_OVERTIME, listAbsent)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("WorkDateFragment", error.message)
        })
    }
}