package vn.aquavietnam.aquaiget.network

import io.reactivex.Observable
import retrofit2.http.*
import vn.aquavietnam.aquaiget.model.*

/**
 * Created by ThanhTuan on 3/21/2018.
 */
public interface AquaAPI {
    companion object {
        const val home1 = "api/banner/image"
        const val login = "api/employee/login"
        const val profile = "api/employee/info"
        const val product = "api/get/products"
        const val bonus = "api/bonus"
        const val listworkdate = "api/daily/inout"
        const val workdatedetail = "api/daily/detail"
        const val logout = "api/employee/logout"
        const val annualleave = "api/annualleave"
        const val nonscan = "api/missingnonscan"
        const val listmanager = "api/absent/manager"
        const val productdetail = "api/product/detail"

        //register
        const val registerDayOff = "api/absent/insert"
        const val registerOverTime = "api/overtime/insert"
        const val registerNonScan = "api/confirm/insert"

        //getlist in Profile
        const val listRegisterDateOff = "api/absent/view"
        const val listRegisterNonScan = "api/confirm/view"
        const val listRegisterOverTime = "api/overtime/view"

        const val listConfirmDateOff = "api/absent/pending/request"
        const val listConfirmNonScan = "api/confirm/pending/request"
        const val listConfirmOverTime = "api/overtime/pending/request"

        const val postRegisterDeviceToken = "api/update/deviceToken/android"
    }

    @GET(home1)
    fun getHome1(): Observable<Home1>

    @GET(annualleave)
    fun getAnuualLeave(): Observable<ResultAnnualLeave>

    @GET(nonscan)
    fun getNonScan(): Observable<ResultNonScan>

    @GET(product)
    fun getProduct(): Observable<ListProduct>

    @GET(listworkdate)
    fun getListWorkDate(): Observable<ListWorkDate>

    @GET(workdatedetail)
    fun getWorkDateDetail(@Query("workDate") workDate: String): Observable<WorkDateDetail>

    @GET(productdetail)
    fun geProductDetail(@Query("productId") workDate: String): Observable<ProductDetail>

    @GET(listmanager)
    fun getListManager(@Query("idManager") idManager: String): Observable<Manager>

    @FormUrlEncoded
    @POST(postRegisterDeviceToken)
    fun registerDeiviceToken(@Field("deviceToken") deviceToken: String): Observable<PostDataResult>

    @FormUrlEncoded
    @POST(login)
    fun login(@Field("employeeId") employeeId: String, @Field("password") password: String): Observable<LoginInfo>

    @FormUrlEncoded
    @POST(registerDayOff)
    fun registerDayOff(@Field("employeeType") employeeType: Int, @Field("date") date: String, @Field("reason") reason: Int,
                       @Field("contents") contents: String, @Field("fromTime") fromTime: String, @Field("fromDate") fromDate: String,
                       @Field("toTime") toTime: String, @Field("toDate") toDate: String, @Field("totalAbsentDays") totalAbsentDays: String,
                       @Field("decision") decision: String, @Field("requestBy") requestBy: String): Observable<PostDataResult>

    @FormUrlEncoded
    @POST(registerOverTime)
    fun registerOverTime(@Field("overtimeType") employeeType: Int, @Field("overtimeDate") date: String, @Field("purpose") purpose: String,
                       @Field("frmTime") fromTime: String, @Field("toTime") toTime: String, @Field("requestBy") requestBy: String): Observable<PostDataResult>


    @FormUrlEncoded
    @POST(registerNonScan)
    fun registerNonScan(@Field("date") date: String, @Field("reason") reason: String,
                         @Field("timeIn") timeIn: String, @Field("timeOut") timeOut: String, @Field("requestBy") requestBy: String): Observable<PostDataResult>



    @GET(profile)
    fun getProfile(): Observable<Employee>

    @GET(listRegisterDateOff)
    fun getListRegisterDateOff(): Observable<ListAbsent>

    @GET(listRegisterNonScan)
    fun getListRegisterNonScan(): Observable<ListNonScan>

    @GET(listRegisterOverTime)
    fun getListRegisterOvertime(): Observable<ListOvertime>

    @GET(listConfirmDateOff)
    fun getConfirmDateOff(): Observable<ListAbsent>

    @GET(listConfirmNonScan)
    fun getConfirmNonScan(): Observable<ListNonScan>

    @GET(listConfirmOverTime)
    fun getConfirmOvertime(): Observable<ListOvertime>

    //profile
    @GET(bonus)
    fun getDiligentBonus(): Observable<ResultBonus>

    @GET(logout)
    fun logout(): Observable<PostDataResult>
}