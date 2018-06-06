package vn.aquavietnam.aquaiget.profile.listregisterdateoff

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.model.Absent
import vn.aquavietnam.aquaiget.model.ListAbsent
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ListRegisterDateOffVM(context: Context?, listAbsent: List<Absent>?): BaseVM()  {
    var listRegisterDateOffAdapter: ListRegisterDateOffAdapter? = null
    init {
        this.context = context
        listRegisterDateOffAdapter = ListRegisterDateOffAdapter(context!!, listAbsent)
        initRecycler(listRegisterDateOffAdapter!!,1)
    }
    fun setAdapter(listWorkDate: List<Absent>?) {
        listRegisterDateOffAdapter!!.arrData = listWorkDate
        listRegisterDateOffAdapter!!.notifyDataSetChanged()

    }
    fun getListRegisterDateOff() {
        AquaService.getListRegisterDateOff().execute().subscribe({ listAbsent ->
            if (listAbsent != null) {
                val dataBus = DataBus(DataBus.GET_LIST_REGISTER_DATEOFF, listAbsent)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("WorkDateFragment", error.message)
        })
    }
}