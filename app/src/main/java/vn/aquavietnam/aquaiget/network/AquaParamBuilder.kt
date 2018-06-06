package vn.aquavietnam.aquaiget.network

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.aquavietnam.aquaiget.model.*

/**
 * Created by ThanhTuan on 3/21/2018.
 */
class AquaParamBuilder {
    companion object {
        fun getHome1Action(): Home1Builder {
            return Home1Builder()
        }
        fun loginAction(): LoginBuilder {
            return LoginBuilder()
        }
        fun getProfileAction(): ProfileBuilder {
            return ProfileBuilder()
        }
        fun getProductAction(): ProductBuilder {
            return ProductBuilder()
        }
        fun getListWorkDateAction(): WorkDateBuilder {
            return WorkDateBuilder()
        }
        fun getDiligentBonusAction(): DiligentBonusBuilder{
            return DiligentBonusBuilder()
        }
        fun getAnnualLeaveAction(): AnnualLeaveBuilder{
            return AnnualLeaveBuilder()
        }
        fun getNonScanAction(): NonScanBuilder{
            return NonScanBuilder()
        }
        fun registerDayOffAction(): RegisterDayOffBuilder{
            return RegisterDayOffBuilder()
        }
        fun registerOvertimeAction(): RegisterOverTimeBuilder{
            return RegisterOverTimeBuilder()
        }
        fun registerNonScanAction(): RegisterNonScanBuilder{
            return RegisterNonScanBuilder()
        }
        fun getListRegisterDateOffAction(): ListRegisterDateOffBuilder{
            return ListRegisterDateOffBuilder()
        }
        fun getListRegisterNonScanAction(): ListRegisterNonScanBuilder{
            return ListRegisterNonScanBuilder()
        }
        fun getListRegisterOvertimeAction(): ListRegisterOvertimeBuilder{
            return ListRegisterOvertimeBuilder()
        }
        fun getConfirmDateOffAction(): ConfirmDateOffBuilder{
            return ConfirmDateOffBuilder()
        }
        fun getConfirmNonScanAction(): ConfirmNonScanBuilder{
            return ConfirmNonScanBuilder()
        }
        fun getConfirmOvertimeAction(): ConfirmOvertimeBuilder{
            return ConfirmOvertimeBuilder()
        }
        fun registerDeviceTokenAction(): RegisterDeviceTokenBuilder{
            return RegisterDeviceTokenBuilder()
        }

        fun getWorkDateDetailBuilderAction() : WorkDateDetailBuilder{
            return WorkDateDetailBuilder()
        }

        fun getListManagerBuilder() : ListManagerBuilder{
            return ListManagerBuilder()
        }

        fun getProductDetailAction() : ProductDetailBuilder{
            return ProductDetailBuilder()
        }


        fun logoutAction(): LogoutBuilder {
            return LogoutBuilder()
        }
    }

    class Home1Builder {
        fun execute(): Observable<Home1> {
            return AquaService.getAquaAPI().getHome1().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class AnnualLeaveBuilder {
        fun execute(): Observable<ResultAnnualLeave> {
            return AquaService.getAquaAPI().getAnuualLeave().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class NonScanBuilder {
        fun execute(): Observable<ResultNonScan> {
            return AquaService.getAquaAPI().getNonScan().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class ProductBuilder {
        fun execute(): Observable<ListProduct> {
            return AquaService.getAquaAPI().getProduct().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class DiligentBonusBuilder {
        fun execute(): Observable<ResultBonus> {
            return AquaService.getAquaAPI().getDiligentBonus().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class WorkDateDetailBuilder {
        lateinit var workDate: String
        fun setWorkDate(workDate: String): WorkDateDetailBuilder {
            this.workDate = workDate
            return this
        }
        fun execute(): Observable<WorkDateDetail> {
            return AquaService.getAquaAPI().getWorkDateDetail(workDate).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class ProductDetailBuilder {
        lateinit var productId: String
        fun setIdProduct(productId: String): ProductDetailBuilder {
            this.productId = productId
            return this
        }
        fun execute(): Observable<ProductDetail> {
            return AquaService.getAquaAPI().geProductDetail(productId).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    class RegisterDeviceTokenBuilder {
        lateinit var deviceToken: String
        fun setDeviceToken(deviceToken: String): RegisterDeviceTokenBuilder {
            this.deviceToken = deviceToken
            return this
        }
        fun execute(): Observable<PostDataResult> {
            return AquaService.getAquaAPI().registerDeiviceToken(deviceToken).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    class ListManagerBuilder {
        lateinit var idManager: String
        fun setIdManage(idManager: String): ListManagerBuilder {
            this.idManager = idManager
            return this
        }
        fun execute(): Observable<Manager> {
            return AquaService.getAquaAPI().getListManager(idManager).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class WorkDateBuilder {
        fun execute(): Observable<ListWorkDate> {
            return AquaService.getAquaAPI().getListWorkDate().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class ListRegisterDateOffBuilder {
        fun execute(): Observable<ListAbsent> {
            return AquaService.getAquaAPI().getListRegisterDateOff().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class ListRegisterOvertimeBuilder {
        fun execute(): Observable<ListOvertime> {
            return AquaService.getAquaAPI().getListRegisterOvertime().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class ListRegisterNonScanBuilder {
        fun execute(): Observable<ListNonScan> {
            return AquaService.getAquaAPI().getListRegisterNonScan().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class ConfirmDateOffBuilder {
        fun execute(): Observable<ListAbsent> {
            return AquaService.getAquaAPI().getConfirmDateOff().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class ConfirmOvertimeBuilder {
        fun execute(): Observable<ListOvertime> {
            return AquaService.getAquaAPI().getConfirmOvertime().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class ConfirmNonScanBuilder {
        fun execute(): Observable<ListNonScan> {
            return AquaService.getAquaAPI().getConfirmNonScan().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class LoginBuilder {
        lateinit var employeeId: String
        lateinit var password: String
        fun setLoginParamater(employeeId: String,password: String): LoginBuilder {
            this.employeeId = employeeId
            this.password = password
            return this
        }

        fun execute(): Observable<LoginInfo> {
            return AquaService.getAquaAPI().login(employeeId,password).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class RegisterDayOffBuilder {
        var employeeType: Int = 1
        lateinit var date: String
        var reason: Int = 1
        lateinit var contents: String
        lateinit var fromTime: String
        lateinit var fromDate: String
        lateinit var toTime: String
        lateinit var toDate: String
        lateinit var totalAbsentDays: String
        lateinit var decision: String
        lateinit var requestBy: String
        fun setRegisterDateOffParameter(
                employeeType: Int,
                date: String,
                reason: Int,
                contents: String,
                fromTime: String,
                fromDate: String,
                toTime: String,
                toDate: String,
                totalAbsentDays: String,
                decision: String,
                requestBy: String): RegisterDayOffBuilder {
            this.employeeType = employeeType
            this.reason = reason
            this.date = date
            this.contents = contents
            this.fromTime = fromTime
            this.fromDate = fromDate
            this.toTime = toTime
            this.toDate = toDate
            this.totalAbsentDays = totalAbsentDays
            this.decision = decision
            this.requestBy = requestBy
            return this
        }

        fun execute(): Observable<PostDataResult> {
            return AquaService.getAquaAPI().registerDayOff(
                    employeeType = employeeType,
                    date = date,
                    reason = reason,
                    contents = contents,
                    fromTime = fromTime,
                    fromDate = fromDate,
                    toTime = toTime,
                    toDate = toDate,
                    totalAbsentDays = totalAbsentDays,
                    decision = decision,
                    requestBy = requestBy).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class RegisterOverTimeBuilder {
        var overtimeType: Int = 1
        lateinit var date: String
        lateinit var purpose: String
        lateinit var fromTime: String
        lateinit var toTime: String
        lateinit var requestBy: String
        fun setRegisterOvertimeParameter(
                overtimeType: Int,
                date: String,
                purpose: String,
                fromTime: String,
                toTime: String,
                requestBy: String): RegisterOverTimeBuilder {
            this.overtimeType = overtimeType
            this.date = date
            this.purpose = purpose
            this.fromTime = fromTime
            this.toTime = toTime
            this.requestBy = requestBy
            return this
        }

        fun execute(): Observable<PostDataResult> {
            return AquaService.getAquaAPI().registerOverTime(
                    employeeType = overtimeType,
                    date = date,
                    purpose = purpose,
                    fromTime = fromTime,
                    toTime = toTime,
                    requestBy = requestBy).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    class RegisterNonScanBuilder {
        lateinit var date: String
        lateinit var reason: String
        lateinit var fromTime: String
        lateinit var toTime: String
        lateinit var requestBy: String
        fun setRegisterNonScanParameter(
                date: String,
                reason: String,
                fromTime: String,
                toTime: String,
                requestBy: String): RegisterNonScanBuilder {
            this.date = date
            this.reason = reason
            this.fromTime = fromTime
            this.toTime = toTime
            this.requestBy = requestBy
            return this
        }

        fun execute(): Observable<PostDataResult> {
            return AquaService.getAquaAPI().registerNonScan(
                    date = date,
                    reason = date,
                    timeOut = toTime,
                    timeIn = fromTime,
                    requestBy = requestBy).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    class ProfileBuilder{
        fun execute(): Observable<Employee> {
            return AquaService.getAquaAPI().getProfile().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
    class LogoutBuilder{
        fun execute(): Observable<PostDataResult> {
            return AquaService.getAquaAPI().logout().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}