package vn.aquavietnam.aquaiget.network

import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import com.bluelinelabs.logansquare.LoganSquare
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import vn.aquavietnam.aquaiget.base.AquaApp
import vn.aquavietnam.aquaiget.base.TabbarActivity
import vn.aquavietnam.aquaiget.login.UserLoginActivity
import vn.aquavietnam.aquaiget.model.AQUA_User
import vn.aquavietnam.aquaiget.model.BoxCate
import vn.aquavietnam.aquaiget.model.Product
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus
import java.util.*

/**
 * Created by ThanhTuan on 3/21/2018.
 */
class LogInterceptor : Interceptor {
    private var request: Request? = null
    private val TAG = "LogInterceptor"
    override fun intercept(chain: Interceptor.Chain?): Response {
        request = chain!!.request()
        val newRequestBuilder = request!!.newBuilder()
        if(AQUA_User.shared!!.userAqua.userToken != null) {
            request = newRequestBuilder.header("AuthorizedToken",AQUA_User.shared!!.userAqua.userToken).build()
        }else {
            request = newRequestBuilder.build()
        }
        val t1 = System.nanoTime()
        val response = chain.proceed(request)
        if(response.code() == 401) {
            val dataBus = DataBus(DataBus.GET_LOG_OUT, "401")
            RxBus.instance.send(dataBus)
        }
        val t2 = System.nanoTime()
        var content = response.body()!!.string()
        Log.v(TAG, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6, response.headers()))
        Log.i(TAG, content)
        return response.newBuilder()
                .body(ResponseBody.create(response.body()!!.contentType(), content))
                .build()
    }
}


