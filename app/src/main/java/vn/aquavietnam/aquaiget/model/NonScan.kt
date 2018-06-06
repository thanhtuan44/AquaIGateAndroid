package vn.aquavietnam.aquaiget.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class ResultNonScan {
    @JsonField(name = arrayOf("status"))
    var status: String? = ""
    @JsonField(name = arrayOf("message"))
    var message: String? = ""
    @JsonField(name = arrayOf("missingNonScan"))
    var data: List<NonScan>? = null
}
@JsonObject
class NonScan: BaseModel() {
    @JsonField(name = arrayOf("workDate"))
    var workDate: String? = ""
    @JsonField(name = arrayOf("missingNonscan"))
    var missingNonscan: String? = ""
    @JsonField(name = arrayOf("timeIn"))
    var timeIn: String? = ""
    @JsonField(name = arrayOf("timeOut"))
    var timeOut: String? = ""
}