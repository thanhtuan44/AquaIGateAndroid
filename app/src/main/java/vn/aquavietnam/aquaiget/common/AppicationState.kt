package vn.aquavietnam.aquaiget.common

import android.app.ActivityManager
import android.content.Context
import android.os.AsyncTask
import android.os.AsyncTask.execute



/**
 * Created by ThanhTuan on 5/29/2018.
 */

class ApplicationState : AsyncTask<Context, Void, Boolean>() {

    protected override fun doInBackground(vararg params: Context): Boolean? {
        val context = params[0].getApplicationContext()
        return isAppOnForeground(context)
    }

    private fun isAppOnForeground(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcesses = activityManager.getRunningAppProcesses() ?: return false
        val packageName = context.getPackageName()
        for (appProcess in appProcesses) {
            if (appProcess.importance === ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                return true
            }
        }
        return false
    }
}

// Use like this:
//var foregroud = ApplicationState().execute(context).get()