package vn.aquavietnam.aquaiget.base

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.tabbar_activity.*
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.service.ServiceFragment
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.view.Menu
import android.widget.RemoteViews
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import vn.aquavietnam.aquaiget.homepage.HomePageFragment
import vn.aquavietnam.aquaiget.login.UserLoginActivity
import vn.aquavietnam.aquaiget.model.AQUA_User
import vn.aquavietnam.aquaiget.product.ProductFragment
import vn.aquavietnam.aquaiget.profile.ProfileFragment
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus


class TabbarActivity : AppCompatActivity() {
    var tabbarDisposable: Disposable? = null
    var notificationDisposable: Disposable? = null
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_HomePage -> {
                supportFragmentManager.beginTransaction().replace(R.id.tabbarContent, HomePageFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_Tool -> {
                supportFragmentManager.beginTransaction().replace(R.id.tabbarContent, ServiceFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_Product -> {
                supportFragmentManager.beginTransaction().replace(R.id.tabbarContent, ProductFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_Accout -> {
                supportFragmentManager.beginTransaction().replace(R.id.tabbarContent, ProfileFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tabbar_activity)
        setSupportActionBar(nav_Base)
        supportActionBar!!.setTitle("")
        supportFragmentManager.beginTransaction().replace(R.id.tabbarContent, HomePageFragment()).commit()
        BottomNavigationViewHelper.removeShiftMode(bottomTabbar)
        bottomTabbar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }
    override fun onPause() {
        super.onPause()
        if (tabbarDisposable != null) {
            tabbarDisposable!!.dispose()
        }
        if (notificationDisposable != null) {
            notificationDisposable!!.dispose()
        }
    }
    override fun onResume() {
        super.onResume()
        tabbarDisposable = RxBus.instance.observableWithEvent(DataBus.GET_LOG_OUT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        val responseCode: String = o.eventValue as String
                        if(responseCode != null) {
                            if(responseCode == "401") {
                                AQUA_User.shared.logoutUser()
                                val intent : Intent = Intent(this, UserLoginActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                })
        notificationDisposable = RxBus.instance.observableWithEvent(DataBus.GET_NOTIFICATION)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        val remoteMessage: RemoteMessage = o.eventValue as RemoteMessage
                        if(remoteMessage != null) {
                            val title = remoteMessage.notification!!.title
                            val body = remoteMessage.notification!!.body
                            val intent = Intent()
                            val pendingIntent = PendingIntent.getActivity(this@TabbarActivity, 0, intent,
                                    PendingIntent.FLAG_ONE_SHOT)
                            val notifyImage = BitmapFactory.decodeResource(resources, R.drawable.iconnotification)
                            val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//                            val notificationView = RemoteViews(this.packageName,R.layout.notification)
//                            notificationView.setImageViewResource(R.id.imgAppIcon,R.drawable.iconnotification)
//                            notificationView.setTextViewText(R.id.txtReminder,"aaaaa")
//                            notificationView.setTextViewText(R.id.txtStartReminder,"bbbb")
                            val notificationBuilder = NotificationCompat.Builder(this,"")
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setLargeIcon(notifyImage)
                                    .setColor(Color.parseColor("#FFE74C3C"))
                                    .setContentTitle(title)
                                    .setContentText(body)
                                    .setAutoCancel(true)
                                    .setSound(defaultSoundUri)
                                    .setContentIntent(pendingIntent)
                            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                            notificationManager.notify(0, notificationBuilder.build())
                        }
                    }
                })
    }
}

internal object BottomNavigationViewHelper {
    @SuppressLint("RestrictedApi")
    fun removeShiftMode(view: BottomNavigationView) {
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView
                item.setShiftingMode(false)
                // set once again checked value, so view will be updated
                item.setChecked(item.itemData.isChecked)
            }
        }catch (e: NoSuchFieldException){
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field")
        }catch (e: IllegalAccessException){
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode")
        }
    }
}


