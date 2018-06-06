package vn.aquavietnam.aquaiget.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

/**
 * Created by ThanhTuan on 5/23/2018.
 */
@JsonObject
class ListOvertime {
    @JsonField(name = arrayOf("status"))
    var status: String? = ""
    @JsonField(name = arrayOf("message"))
    var message: String? = ""
    @JsonField(name = arrayOf("data"))
    var data: List<Overtime>? = null
}
@JsonObject
class Overtime: BaseModel() {
    @JsonField(name = arrayOf("avatar"))
    var avartar: String? = ""
    @JsonField(name = arrayOf("department"))
    var department: String? = ""
    @JsonField(name = arrayOf("frmTime"))
    var frmTime: String? = ""
    @JsonField(name = arrayOf("fullName"))
    var fullName: String? = ""
    @JsonField(name = arrayOf("id"))
    var id: String? = ""
    @JsonField(name = arrayOf("overtimeDate"))
    var overtimeDate: String? = ""
    @JsonField(name = arrayOf("overtimeType"))
    var overtimeType: String? = ""
    @JsonField(name = arrayOf("position"))
    var position: String? = ""
    @JsonField(name = arrayOf("purpose"))
    var purpose: String? = ""
    @JsonField(name = arrayOf("requestBy"))
    var requestBy: String? = ""
    @JsonField(name = arrayOf("section"))
    var section: String? = ""
    @JsonField(name = arrayOf("status"))
    var status: String? = ""
    @JsonField(name = arrayOf("toTime"))
    var toTime: String? = ""
}