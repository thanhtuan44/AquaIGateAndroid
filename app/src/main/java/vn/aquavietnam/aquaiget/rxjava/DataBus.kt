package vn.aquavietnam.aquaiget.rxjava

/**
 * Created by ThanhTuan on 3/27/2018.
 */
class DataBus<T> {
    var eventName = "default"
    var eventValue: T? = null

    constructor() {}

    constructor(eventName: String, eventValue: T) {
        this.eventName = eventName
        this.eventValue = eventValue
    }

    companion object {
        val GET_HOME1_EVENT = "GET_HOME1_EVENT"
        val GET_WORKDATE_LIST_EVENT = "GET_WORKDATE_LIST_EVENT"
        val GET_NONSCAN_LIST_EVENT = "GET_NONSCAN_LIST_EVENT"
        val GET_ANNUALLEAVE_LIST_EVENT = "GET_ANNUALLEAVE_LIST_EVENT"
        val GET_WORKDATE_DETAIL = "GET_WORKDATE_DETAIL"
        val GET_LIST_MANAGER = "GET_LIST_MANAGER"
        val GET_LOGIN_EVENT = "GET_LOGIN_EVENT"
        val GET_LOGOUT_EVENT = "GET_LOGOUT_EVENT"
        val GET_PROFILE_EVENT = "GET_PROFILE_EVENT"
        val GET_PROFILE = "GET_PROFILE"
        val GET_PRODUCT_EVENT = "GET_PRODUCT_EVENT"
        val REGISTER_DAY_OFF = "REGISTER_DAY_OFF"
        val REGISTER_OVERTIME = "REGISTER_OVERTIME"
        val REGISTER_NONSCAN = "REGISTER_NONSCAN"
        val GET_DILIGENT_BONUS_EVENT = "GET_DILIGENT_BONUS_EVENT"
        val GET_PRODUCT_DETAIL = "GET_PRODUCT_DETAIL"

        val GET_LIST_REGISTER_DATEOFF = "GET_LIST_REGISTER_DATEOFF"
        val GET_LIST_REGISTER_OVERTIME = "GET_LIST_REGISTER_OVERTIME"
        val GET_LIST_REGISTER_NONSCAN = "GET_LIST_REGISTER_NONSCAN"

        val GET_LIST_CONFIRM_DATEOFF = "GET_LIST_CONFIRM_DATEOFF"
        val GET_LIST_CONFIRM_OVERTIME = "GET_LIST_CONFIRM_OVERTIME"
        val GET_LIST_CONFIRM_NONSCAN = "GET_LIST_CONFIRM_NONSCAN"

        val GET_NOTIFICATION = "GET_NOTIFICATION"

        val GET_LOG_OUT = "GET_LOG_OUT"
    }
}