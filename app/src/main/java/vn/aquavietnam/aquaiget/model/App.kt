package vn.aquavietnam.aquaiget.model

import android.app.Application
import com.google.gson.Gson

/**
 * Created by ThanhTuan on 5/3/2018.
 */

class App : Application() {
    var gSon: Gson? = null
        private set

    override fun onCreate() {
        super.onCreate()
        mSelf = this
        gSon = Gson()
    }

    companion object {
        private var mSelf: App? = null

        fun self(): App? {
            return mSelf
        }
    }
}
