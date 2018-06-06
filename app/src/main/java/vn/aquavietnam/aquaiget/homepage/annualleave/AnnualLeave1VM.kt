package vn.aquavietnam.aquaiget.homepage.annualleave

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.model.AnnualLeaveInfo
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

class AnnualLeave1VM(context: Context?): BaseVM() {
    var annualLeaveAdapter: AnnualLeaveAdapter? = null
    init {
        this.context = context
        annualLeaveAdapter = AnnualLeaveAdapter(context!!, null)
        initRecycler(annualLeaveAdapter!!,1)
    }
    fun setAdapter(listAnnualLeave: List<AnnualLeaveInfo>?) {
        annualLeaveAdapter!!.arrData = listAnnualLeave
        annualLeaveAdapter!!.notifyDataSetChanged()

    }
    fun getListAnnualLeave() {
        AquaService.getAnnualLeaveService().execute().subscribe({ listAnnualLeave ->
            if (listAnnualLeave != null) {
                val dataBus = DataBus(DataBus.GET_ANNUALLEAVE_LIST_EVENT, listAnnualLeave)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("NonScanFragment", error.message)
        })
    }
}