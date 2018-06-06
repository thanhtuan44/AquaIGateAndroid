package vn.aquavietnam.aquaiget.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

/**
 * Created by ThanhTuan on 5/12/2018.
 */
@JsonObject
class Manager {
    @JsonField(name = arrayOf("data"))
    var managerInfo: List<ManagerInfo>? = null
}
@JsonObject
class ManagerInfo {
    @JsonField(name = arrayOf("id"))
    var idManager: String? = null
    @JsonField(name = arrayOf("text"))
    var nameManager: String? = null
}