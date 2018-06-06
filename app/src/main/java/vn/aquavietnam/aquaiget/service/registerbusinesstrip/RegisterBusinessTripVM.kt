package vn.aquavietnam.aquaiget.service.registerbusinesstrip

import android.content.Context
import android.util.Log
import android.widget.Toast
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/31/2018.
 */
class RegisterBusinessTripVM (context: Context?): BaseVM()  {
    var showLoading: (() -> Unit)? =  null
    init {
        this.context = context
    }
    fun getProfile() {
        Log.d("TEXT","VALUE")
        AquaService.getProfileService().execute().subscribe({ profileInfo ->
            if (profileInfo != null) {
                Log.v("BusinessFragment", "3213")
                val dataBus = DataBus(DataBus.GET_PROFILE_EVENT, profileInfo)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("BusinessFragment", error.message)
        })
    }
    fun registerDayOff(employeeType:String, employeeId: Int, date: String,reason:String, reasonId: Int, content: String, fromTime: String, fromDate: String, toTime: String, toDate: String, totalAbsentDays: String,
                       decision: String, requestBy: String) {
        if(employeeType == "Chọn loại nhân viên" || employeeType == ""){
            Toast.makeText(this.context,"Vui lòng chọn loại nhân viên", Toast.LENGTH_LONG).show()
            return
        }
        if(reason == "Chọn lý do nghỉ" || reason == ""){
            Toast.makeText(this.context,"Vui lòng chọn lý do nghỉ", Toast.LENGTH_LONG).show()
            return
        }
        if(totalAbsentDays == ""){
            Toast.makeText(this.context,"Vui lòng nhập tổng số ngày nghỉ", Toast.LENGTH_LONG).show()
            return
        }
        if(requestBy == ""){
            Toast.makeText(this.context,"Vui lòng nhập tổng số ngày nghỉ", Toast.LENGTH_LONG).show()
            return
        }
        showLoading?.invoke()
//        AquaService.registerDayOffService()
//                .setRegisterBusinessTripParameter(
//                        employeeType = employeeId,
//                        date = date,
//                        reason = reasonId,
//                        contents = content,
//                        fromTime = fromTime,
//                        fromDate = fromDate,
//                        toTime = toTime,
//                        toDate = toDate,
//                        totalAbsentDays = totalAbsentDays,
//                        decision = decision,
//                        requestBy = requestBy)
//                .execute().subscribe({ o ->
//                    if (o != null) {
//                        val dataBus = DataBus(DataBus.REGISTER_DAY_OFF, o)
//                        RxBus.instance.send(dataBus)
//                    }
//                }, { error ->
//                    Log.v("UserLoginActivity", error.message)
//                })
    }
}