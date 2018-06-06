package vn.aquavietnam.aquaiget.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

/**
 * Created by ThanhTuan on 5/23/2018.
 */
@JsonObject
class ListNonScan {
    @JsonField(name = arrayOf("status"))
    var status: String? = ""
    @JsonField(name = arrayOf("message"))
    var message: String? = ""
    @JsonField(name = arrayOf("data"))
    var data: List<MissingNonScan>? = null
}
@JsonObject
class MissingNonScan: BaseModel() {
    @JsonField(name = arrayOf("date"))
    var date: String? = ""
    @JsonField(name = arrayOf("employeeId"))
    var employeeId: String? = ""
    @JsonField(name = arrayOf("fullName"))
    var fullName: String? = ""
    @JsonField(name = arrayOf("id"))
    var id: String? = ""
    @JsonField(name = arrayOf("reason"))
    var reason: String? = ""
    @JsonField(name = arrayOf("requestBy"))
    var requestBy: String? = ""
    @JsonField(name = arrayOf("statusName"))
    var statusName: String? = ""
    @JsonField(name = arrayOf("timeIn"))
    var timeIn: String? = ""
    @JsonField(name = arrayOf("timeOut"))
    var timeOut: String? = ""

}