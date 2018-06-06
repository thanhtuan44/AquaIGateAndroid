package vn.aquavietnam.aquaiget.homepage.workdatedetail

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.homepage.workdate.WorkDateAdapter
import vn.aquavietnam.aquaiget.model.WorkDate
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/7/2018.
 */
class WorkDateDetailVM(context: Context?): BaseVM()  {
    init {
        this.context = context
    }

    fun getWorkDateDetail(workDate: String) {
        Log.d("WORKDATE",workDate)
        AquaService.getListWorkDateDetailService().setWorkDate(workDate).execute().subscribe({ workDateDetail ->
            if (workDateDetail != null) {
                Log.v("WorkDateDetailFragment", "3213")
                val dataBus = DataBus(DataBus.GET_WORKDATE_DETAIL, workDateDetail)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("WorkDateDetailFragment", error.message)
        })
    }
}