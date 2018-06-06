package vn.aquavietnam.aquaiget.network

import com.github.aurae.retrofit2.LoganSquareConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by ThanhTuan on 3/21/2018.
 */
object HttpClient {
    fun getRetrofit(): Retrofit {
        val okHttpClient = initHttpClient()
        return Retrofit.Builder()
                .baseUrl("http://webapi.aquavietnam.vn/HRMS/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(LoganSquareConverterFactory.create())
                .build()
    }

    fun initHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(30000, TimeUnit.MILLISECONDS)
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .addInterceptor(LogInterceptor())
                .build()
    }
}