package vn.aquavietnam.aquaiget.service.registerovertime

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
class RegisterOverTimeVM(context: Context?): BaseVM()  {
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

    fun registerOvertime(overtimeType:String, overtimeTypeId: Int, date: String, purpose:String, fromTime: String, toTime: String, requestBy: String) {
        if(overtimeType == "Chọn loại nhân viên" || overtimeType == ""){
            Toast.makeText(this.context,"Vui lòng chọn loại nhân viên", Toast.LENGTH_LONG).show()
            return
        }
        if(requestBy == ""){
            Toast.makeText(this.context,"Vui lòng nhập tổng số ngày nghỉ", Toast.LENGTH_LONG).show()
            return
        }
        showLoading?.invoke()
        AquaService.registerOvertimeService()
                .setRegisterOvertimeParameter(
                        overtimeType = overtimeTypeId,
                        date = date,
                        purpose = purpose,
                        fromTime = fromTime,
                        toTime = toTime,
                        requestBy = requestBy
                )
                .execute().subscribe({ o ->
                    if (o != null) {
                        val dataBus = DataBus(DataBus.REGISTER_OVERTIME, o)
                        RxBus.instance.send(dataBus)
                    }
                }, { error ->
                    Log.v("UserLoginActivity", error.message)
                })
    }
}