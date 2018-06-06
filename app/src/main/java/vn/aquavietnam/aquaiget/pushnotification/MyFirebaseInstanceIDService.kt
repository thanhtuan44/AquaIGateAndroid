package vn.aquavietnam.aquaiget.pushnotification

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import vn.aquavietnam.aquaiget.model.SharedPrefs

/**
 * Created by ThanhTuan on 5/28/2018.
 */
class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d("Token", "Refreshed token: " + refreshedToken)
        if(refreshedToken != null) {
            SharedPrefs.instance.put("DeviceToken",refreshedToken)
        }
    }
}