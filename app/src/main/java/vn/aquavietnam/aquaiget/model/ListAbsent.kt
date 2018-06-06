package vn.aquavietnam.aquaiget.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

/**
 * Created by ThanhTuan on 5/23/2018.
 */
@JsonObject
class ListAbsent {
    @JsonField(name = arrayOf("status"))
    var status: String? = ""
    @JsonField(name = arrayOf("message"))
    var message: String? = ""
    @JsonField(name = arrayOf("absents"))
    var absents: List<Absent>? = null
}
@JsonObject
class Absent: BaseModel() {
    @JsonField(name = arrayOf("avatar"))
    var avartar: String? = ""
    @JsonField(name = arrayOf("contents"))
    var contents: String? = ""
    @JsonField(name = arrayOf("date"))
    var date: String? = ""
    @JsonField(name = arrayOf("decision"))
    var decision: String? = ""
    @JsonField(name = arrayOf("department"))
    var department: String? = ""
    @JsonField(name = arrayOf("employeeId"))
    var employeeId: String? = ""
    @JsonField(name = arrayOf("employeeName"))
    var employeeName: String? = ""
    @JsonField(name = arrayOf("employeeType"))
    var employeeType: String? = ""
    @JsonField(name = arrayOf("fromDate"))
    var fromDate: String? = ""
    @JsonField(name = arrayOf("fromTime"))
    var fromTime: String? = ""
    @JsonField(name = arrayOf("id"))
    var id: String? = ""
//    @JsonField(name = arrayOf("isRequest"))
//    var isRequest: String? = ""
    @JsonField(name = arrayOf("note"))
    var note: String? = ""
    @JsonField(name = arrayOf("reason"))
    var reason: String? = ""
    @JsonField(name = arrayOf("requestBy"))
    var requestBy: String? = ""
    @JsonField(name = arrayOf("section"))
    var section: String? = ""
    @JsonField(name = arrayOf("status"))
    var status: String? = ""
    @JsonField(name = arrayOf("toDate"))
    var toDate: String? = ""
    @JsonField(name = arrayOf("toTime"))
    var toTime: String? = ""
    @JsonField(name = arrayOf("totalAbsentDays"))
    var totalAbsentDays: String? = ""

}