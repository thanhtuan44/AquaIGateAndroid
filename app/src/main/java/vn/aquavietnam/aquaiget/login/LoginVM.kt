package vn.aquavietnam.aquaiget.login

import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

class LoginVM(): BaseVM() {
    fun login(employeeId: String, password: String) {
        AquaService.loginService()
                .setLoginParamater(employeeId,password)
                .execute().subscribe({ o ->
                    if (o != null) {
                        val dataBus = DataBus(DataBus.GET_LOGIN_EVENT, o)
                        RxBus.instance.send(dataBus)
                    }
                }, { error ->
                    Log.v("UserLoginActivity", error.message)
                })

    }
}