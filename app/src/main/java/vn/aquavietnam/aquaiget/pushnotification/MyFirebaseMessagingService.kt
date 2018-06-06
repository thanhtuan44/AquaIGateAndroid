package vn.aquavietnam.aquaiget.pushnotification

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.content.Context.NOTIFICATION_SERVICE
import android.app.NotificationManager
import android.graphics.Color.parseColor
import android.media.RingtoneManager
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.app.NotificationCompat
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.TabbarActivity
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus


/**
 * Created by ThanhTuan on 5/28/2018.
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "JSA-FCM"

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        if(remoteMessage != null) {
            if (remoteMessage!!.notification != null) {
                Log.e(TAG, "Title: " + remoteMessage.notification?.title)
                Log.e(TAG, "Body: " + remoteMessage.notification?.body)
                showNotification(remoteMessage)
            }

            if (remoteMessage.data.isNotEmpty()) {
                Log.e(TAG, "Data: " + remoteMessage?.data)
            }
        }
    }

    private fun showNotification(remoteMessage: RemoteMessage) {
        val dataBus = DataBus(DataBus.GET_NOTIFICATION, remoteMessage)
        RxBus.instance.send(dataBus)
        /*
        val title = remoteMessage.notification!!.title
        val body = remoteMessage.notification!!.body
        val intent = Intent(this,TabbarActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(baseContext, 0, intent,
                PendingIntent.FLAG_ONE_SHOT)
        val notifyImage = BitmapFactory.decodeResource(resources, R.drawable.iconnotification)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this,"")
                .setSmallIcon(R.drawable.iconnotification)
                .setLargeIcon(notifyImage)
                .setColor(Color.parseColor("#FFE74C3C"))
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
        */
    }

}