package vn.aquavietnam.aquaiget.profile.confirmnonscan

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.model.MissingNonScan
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ConfirmNonScanVM (context: Context?, listNonScan: List<MissingNonScan>?): BaseVM()   {
    var confirmNonScanAdapter: ConfirmNonScanAdapter? = null
    init {
        this.context = context
        confirmNonScanAdapter = ConfirmNonScanAdapter(context!!, listNonScan)
        initRecycler(confirmNonScanAdapter!!,1)
    }
    fun setAdapter(listNonScan: List<MissingNonScan>?) {
        confirmNonScanAdapter!!.arrData = listNonScan
        confirmNonScanAdapter!!.notifyDataSetChanged()

    }
    fun getListConfirmNonScan() {
        AquaService.getConfirmNonScan().execute().subscribe({ listNonScan ->
            if (listNonScan != null) {
                val dataBus = DataBus(DataBus.GET_LIST_CONFIRM_NONSCAN, listNonScan)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("WorkDateFragment", error.message)
        })
    }
}