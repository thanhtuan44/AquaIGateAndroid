package vn.aquavietnam.aquaiget.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class ListWorkDate {
    @JsonField(name = arrayOf("status"))
    var status: String? = ""
    @JsonField(name = arrayOf("message"))
    var message: String? = ""
    @JsonField(name = arrayOf("daily"))
    var data: List<WorkDate>? = null
    @JsonField(name = arrayOf("user"))
    var user: EmployeeInfo? = EmployeeInfo()
}
@JsonObject
class WorkDateDetail: BaseModel() {
    @JsonField(name = arrayOf("status"))
    var status: String? = ""
    @JsonField(name = arrayOf("message"))
    var message: String? = ""
    @JsonField(name = arrayOf("daily"))
    var workdate: WorkDate? = WorkDate()
}
@JsonObject
class WorkDate : BaseModel() {
    @JsonField(name = arrayOf("employee"))
    var employee: String? = ""
    @JsonField(name = arrayOf("workDate"))
    var workDate: String? = ""
    @JsonField(name = arrayOf("timeOut"))
    var timeOut: String? = ""
    @JsonField(name = arrayOf("timeIn"))
    var timeIn: String? = ""
    @JsonField(name = arrayOf("Shift"))
    var shift: String? = ""
    @JsonField(name = arrayOf("actualTime"))
    var actualTime: String? = ""
    @JsonField(name = arrayOf("NS"))
    var ns: String? = ""
    @JsonField(name = arrayOf("NS15"))
    var ns15: String? = ""
    @JsonField(name = arrayOf("NS20"))
    var ns20: String? = ""
    @JsonField(name = arrayOf("OT15"))
    var ot15: String? = ""
    @JsonField(name = arrayOf("OTM"))
    var otm: String? = ""
    @JsonField(name = arrayOf("OT20"))
    var ot20: String? = ""
    @JsonField(name = arrayOf("LT"))
    var lt: String? = ""
    @JsonField(name = arrayOf("ET"))
    var et: String? = ""
    @JsonField(name = arrayOf("UT"))
    var ut: String? = ""
    @JsonField(name = arrayOf("MEAL"))
    var meal: String? = ""
    @JsonField(name = arrayOf("TotalWorkTime"))
    var totalWorkTime: String? = ""
}