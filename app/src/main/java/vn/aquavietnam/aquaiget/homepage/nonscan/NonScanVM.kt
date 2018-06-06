package vn.aquavietnam.aquaiget.homepage.nonscan

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.model.NonScan
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

class NonScanVM(context: Context?, listNonScan: List<NonScan>?): BaseVM() {
    var nonScanAdapter: NonScanAdapter? = null
    init {
        this.context = context
        nonScanAdapter = NonScanAdapter(context!!, listNonScan)
        initRecycler(nonScanAdapter!!,1)
    }
    fun setAdapter(listWorkDate: List<NonScan>?) {
        nonScanAdapter!!.arrData = listWorkDate
        nonScanAdapter!!.notifyDataSetChanged()

    }
    fun getListNonScan() {
        AquaService.getNonScanService().execute().subscribe({ listNonScan ->
            if (listNonScan != null) {
                val dataBus = DataBus(DataBus.GET_NONSCAN_LIST_EVENT, listNonScan)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("NonScanFragment", error.message)
        })
    }
}