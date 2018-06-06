package vn.aquavietnam.aquaiget.homepage.workdate

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.model.Product
import vn.aquavietnam.aquaiget.model.WorkDate
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/7/2018.
 */
class WorkDateVM(context: Context?, listWorkDate: List<WorkDate>?): BaseVM()  {
    var workDateAdapter: WorkDateAdapter? = null
    init {
        this.context = context
        workDateAdapter = WorkDateAdapter(context!!, listWorkDate)
        initRecycler(workDateAdapter!!,1)
    }
    fun setAdapter(listWorkDate: List<WorkDate>?) {
        workDateAdapter!!.arrData = listWorkDate
        workDateAdapter!!.notifyDataSetChanged()

    }
    fun getListWorkDate() {
        AquaService.getListWorkDateService().execute().subscribe({ listWorkDate ->
            if (listWorkDate != null) {
                val dataBus = DataBus(DataBus.GET_WORKDATE_LIST_EVENT, listWorkDate)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("WorkDateFragment", error.message)
        })
    }
}