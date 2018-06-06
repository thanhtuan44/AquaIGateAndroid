package vn.aquavietnam.aquaiget.base

import android.app.Application
import android.content.Context

/**
 * Created by ThanhTuan on 3/27/2018.
 */
class AquaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    companion object {
        @get:Synchronized
        var application: AquaApp? = null
            private set

        val context: Context
            get() = application!!.applicationContext
    }
}