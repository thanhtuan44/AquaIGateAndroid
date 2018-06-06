package vn.aquavietnam.aquaiget.model

import android.databinding.BaseObservable
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class ResultAnnualLeave{
    @JsonField(name = arrayOf("status"))
    var status: String? = ""
    @JsonField(name = arrayOf("message"))
    var message: String? = ""
    @JsonField(name = arrayOf("annualleave"))
    var data: List<AnnualLeave>? = null
}
@JsonObject
class AnnualLeave: BaseModel() {
    @JsonField(name = arrayOf("currentYear"))
    var currentYear: String? = ""
    @JsonField(name = arrayOf("joinDate"))
    var joinDate: String? = ""
    @JsonField(name = arrayOf("totalEntitlement"))
    var totalEntitlement: String? = ""
    @JsonField(name = arrayOf("additionalEarn"))
    var additionalEarn: String? = ""
    @JsonField(name = arrayOf("lastYear"))
    var lastYear: String? = ""
    @JsonField(name = arrayOf("earnedLeave"))
    var earnedLeave: String? = ""
    @JsonField(name = arrayOf("leaveTaken"))
    var leaveTaken: String? = ""
    @JsonField(name = arrayOf("totalSickLeave"))
    var totalSickLeave: String? = ""
    @JsonField(name = arrayOf("balance"))
    var balance: String? = ""
}

data class AnnualLeaveInfo(var title: String, var value: String) : BaseObservable()