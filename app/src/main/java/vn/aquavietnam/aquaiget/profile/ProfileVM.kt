package vn.aquavietnam.aquaiget.profile

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

class ProfileVM(context: Context?) :BaseVM() {
    init {
        this.context = context
    }
    fun getProfile() {
        AquaService.getProfileService().execute().subscribe({ profile ->
            if (profile != null) {
                Log.v("ProfileFragment", "OK")
                val dataBus = DataBus(DataBus.GET_PROFILE, profile)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("ProfileFragment", error.message)
        })
    }
    fun logout() {
        AquaService.logoutService().execute().subscribe({ logout ->
            if (logout != null) {
                Log.v("ProfileFragment", "LOGOUT")
                val dataBus = DataBus(DataBus.GET_LOGOUT_EVENT, logout)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("ProfileFragment", error.message)
        })
    }
}