package vn.aquavietnam.aquaiget.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class PostDataResult {
    @JsonField(name = arrayOf("status"))
    var status: String? = null
    @JsonField(name = arrayOf("message"))
    var message: String? = null
}