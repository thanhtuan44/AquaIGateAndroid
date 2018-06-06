package vn.aquavietnam.aquaiget.service.registernonscan

import android.content.Context
import android.util.Log
import android.widget.Toast
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/9/2018.
 */
class RegisterNonScanVM(context: Context?): BaseVM()  {
    var showLoading: (() -> Unit)? =  null
    init {
        this.context = context
    }

    fun getProfile() {
        Log.d("TEXT","VALUE")
        AquaService.getProfileService().execute().subscribe({ profileInfo ->
            if (profileInfo != null) {
                Log.v("RegisterDateOffFragment", "3213")
                val dataBus = DataBus(DataBus.GET_PROFILE_EVENT, profileInfo)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("RegisterDateOffFragment", error.message)
        })
    }
    fun registerNonScan(date: String, reason:String, timeIn: String, timeOut: String, requestBy: String) {
        if(requestBy == ""){
            Toast.makeText(this.context,"Vui lòng nhập tổng số ngày nghỉ", Toast.LENGTH_LONG).show()
            return
        }
        showLoading?.invoke()
        AquaService.registerNonScanService()
                .setRegisterNonScanParameter(
                        date = date,
                        fromTime = timeIn,
                        toTime = timeOut,
                        requestBy = requestBy,
                        reason = reason)
                .execute().subscribe({ o ->
                    if (o != null) {
                        val dataBus = DataBus(DataBus.REGISTER_NONSCAN, o)
                        RxBus.instance.send(dataBus)
                    }
                }, { error ->
                    Log.v("UserLoginActivity", error.message)
                })
    }
}