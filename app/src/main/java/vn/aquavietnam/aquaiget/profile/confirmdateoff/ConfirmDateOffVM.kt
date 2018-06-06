package vn.aquavietnam.aquaiget.profile.confirmdateoff

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.model.Absent
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ConfirmDateOffVM(context: Context?, listAbsent: ArrayList<Absent>?): BaseVM()   {
    var confirmDateOffAdapter: ConfirmDateOffAdapter? = null
    init {
        this.context = context
        confirmDateOffAdapter = ConfirmDateOffAdapter(context!!, listAbsent)
        initRecycler(confirmDateOffAdapter!!,1)
    }
    fun setAdapter(listDateOff: ArrayList<Absent>?) {
        confirmDateOffAdapter!!.arrData = listDateOff
        confirmDateOffAdapter!!.notifyDataSetChanged()

    }
    fun getListConfirmDateOff() {
        AquaService.getConfirmDateOff().execute().subscribe({ listAbsent ->
            if (listAbsent != null) {
                val dataBus = DataBus(DataBus.GET_LIST_CONFIRM_DATEOFF, listAbsent)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("WorkDateFragment", error.message)
        })
    }
}