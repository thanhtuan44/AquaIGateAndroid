package vn.aquavietnam.aquaiget.model

import android.databinding.BaseObservable
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class ResultBonus {
    @JsonField(name = arrayOf("status"))
    var status: String? = ""
    @JsonField(name = arrayOf("message"))
    var message: String? = ""
    @JsonField(name = arrayOf("diligentBonus"))
    var data: Bonus? = null
}

@JsonObject
class Bonus {
    @JsonField(name = arrayOf("bonus"))
    var bonus: String? = ""
    @JsonField(name = arrayOf("inLate"))
    var inLate: String? = ""
    @JsonField(name = arrayOf("outEarly"))
    var outEarly: String? = ""
    @JsonField(name = arrayOf("UnpaidTime"))
    var unpaidTime: String? = ""
    @JsonField(name = arrayOf("NoneScan"))
    var noneScan: String? = ""
    @JsonField(name = arrayOf("LeaveNoBonus"))
    var leaveNoBonus: String? = ""
    @JsonField(name = arrayOf("DayOff"))
    var dayOff: String? = ""
    @JsonField(name = arrayOf("ChangeShift"))
    var changeShift: String? = ""
}

data class BonusInfo(var title: String, var value: String) : BaseObservable()