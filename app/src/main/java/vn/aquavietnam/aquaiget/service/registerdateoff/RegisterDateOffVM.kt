package vn.aquavietnam.aquaiget.service.registerdateoff

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.common.Utilities
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/8/2018.
 */
class RegisterDateOffVM(context: Context?): BaseVM()  {
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
    fun registerDayOff(context:Context,employeeType:String, employeeId: Int, date: String,reason:String, reasonId: Int, content: String, fromTime: String, fromDate: String, toTime: String, toDate: String, totalAbsentDays: String,
                        decision: String, requestBy: String) {
        if(employeeType == "Chọn loại nhân viên" || employeeType == ""){
            Utilities.showAlert(context,"Vui lòng chọn loại nhân viên")
            return
        }
        if(reason == "Chọn lý do nghỉ" || reason == ""){
            Utilities.showAlert(context,"Vui lòng chọn lý do nghỉ")
            return
        }
        if(totalAbsentDays == ""){
            Utilities.showAlert(context,"Vui lòng nhập tổng số ngày nghỉ")
            return
        }
        if(requestBy == ""){
            Utilities.showAlert(context,"Vui lòng chọn người nhận")
            return
        }
        showLoading?.invoke()
        AquaService.registerDayOffService()
            .setRegisterDateOffParameter(
                    employeeType = employeeId,
                    date = date,
                    reason = reasonId,
                    contents = content,
                    fromTime = fromTime,
                    fromDate = fromDate,
                    toTime = toTime,
                    toDate = toDate,
                    totalAbsentDays = totalAbsentDays,
                    decision = decision,
                    requestBy = requestBy)
            .execute().subscribe({ o ->
                if (o != null) {
                    val dataBus = DataBus(DataBus.REGISTER_DAY_OFF, o)
                    RxBus.instance.send(dataBus)
                }
            }, { error ->
                Log.v("UserLoginActivity", error.message)
            })
    }
}