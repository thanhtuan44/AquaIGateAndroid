package vn.aquavietnam.aquaiget.profile.listregisternonscan

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.model.Absent
import vn.aquavietnam.aquaiget.model.MissingNonScan
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ListRegisterNonScanVM(context: Context?, listNonScan: List<MissingNonScan>?): BaseVM()  {
    var listRegisterNonScanAdapter: ListRegisterNonScanAdapter? = null
    init {
        this.context = context
        listRegisterNonScanAdapter = ListRegisterNonScanAdapter(context!!, listNonScan)
        initRecycler(listRegisterNonScanAdapter!!,1)
    }
    fun setAdapter(listWorkDate: List<MissingNonScan>?) {
        listRegisterNonScanAdapter!!.arrData = listWorkDate
        listRegisterNonScanAdapter!!.notifyDataSetChanged()

    }
    fun getListRegisterNonScan() {
        AquaService.getListRegisterNonScan().execute().subscribe({ listNonScan ->
            if (listNonScan != null) {
                val dataBus = DataBus(DataBus.GET_LIST_REGISTER_NONSCAN, listNonScan)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("WorkDateFragment", error.message)
        })
    }
}