package vn.aquavietnam.aquaiget.network

/**
 * Created by ThanhTuan on 3/21/2018.
 */
object AquaService {
    fun getAquaAPI(): AquaAPI {
        return HttpClient.getRetrofit().create(AquaAPI::class.java)
    }
    fun getHome1Service(): AquaParamBuilder.Home1Builder {
        return AquaParamBuilder.getHome1Action()
    }
    fun loginService(): AquaParamBuilder.LoginBuilder {
        return AquaParamBuilder.loginAction()
    }
    fun getProfileService(): AquaParamBuilder.ProfileBuilder {
        return AquaParamBuilder.getProfileAction()
    }
    fun getProductService(): AquaParamBuilder.ProductBuilder {
        return AquaParamBuilder.getProductAction()
    }
    fun getProductDetailService(): AquaParamBuilder.ProductDetailBuilder {
        return AquaParamBuilder.getProductDetailAction()
    }
    fun getListWorkDateService(): AquaParamBuilder.WorkDateBuilder {
        return AquaParamBuilder.getListWorkDateAction()
    }
    fun getListWorkDateDetailService(): AquaParamBuilder.WorkDateDetailBuilder {
        return AquaParamBuilder.getWorkDateDetailBuilderAction()
    }
    fun getListManagerService(): AquaParamBuilder.ListManagerBuilder {
        return AquaParamBuilder.getListManagerBuilder()
    }
    fun getDiligentBonusService(): AquaParamBuilder.DiligentBonusBuilder {
        return AquaParamBuilder.getDiligentBonusAction()
    }
    fun getAnnualLeaveService(): AquaParamBuilder.AnnualLeaveBuilder {
        return AquaParamBuilder.getAnnualLeaveAction()
    }
    fun getNonScanService(): AquaParamBuilder.NonScanBuilder {
        return AquaParamBuilder.getNonScanAction()
    }
    fun registerDayOffService(): AquaParamBuilder.RegisterDayOffBuilder {
        return AquaParamBuilder.registerDayOffAction()
    }
    fun registerOvertimeService(): AquaParamBuilder.RegisterOverTimeBuilder {
        return AquaParamBuilder.registerOvertimeAction()
    }
    fun registerNonScanService(): AquaParamBuilder.RegisterNonScanBuilder {
        return AquaParamBuilder.registerNonScanAction()
    }
    fun getListRegisterDateOff(): AquaParamBuilder.ListRegisterDateOffBuilder {
        return AquaParamBuilder.getListRegisterDateOffAction()
    }
    fun getListRegisterNonScan(): AquaParamBuilder.ListRegisterNonScanBuilder {
        return AquaParamBuilder.getListRegisterNonScanAction()
    }
    fun getListRegisterOvertime(): AquaParamBuilder.ListRegisterOvertimeBuilder {
        return AquaParamBuilder.getListRegisterOvertimeAction()
    }
    fun getConfirmDateOff(): AquaParamBuilder.ConfirmDateOffBuilder {
        return AquaParamBuilder.getConfirmDateOffAction()
    }
    fun getConfirmNonScan(): AquaParamBuilder.ConfirmNonScanBuilder {
        return AquaParamBuilder.getConfirmNonScanAction()
    }
    fun getConfirmOvertime(): AquaParamBuilder.ConfirmOvertimeBuilder {
        return AquaParamBuilder.getConfirmOvertimeAction()
    }
    fun registerDeviceTokenService(): AquaParamBuilder.RegisterDeviceTokenBuilder {
        return AquaParamBuilder.registerDeviceTokenAction()
    }
    fun logoutService(): AquaParamBuilder.LogoutBuilder {
        return AquaParamBuilder.logoutAction()
    }
}