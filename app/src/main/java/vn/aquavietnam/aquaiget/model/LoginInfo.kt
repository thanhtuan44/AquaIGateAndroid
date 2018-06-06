package vn.aquavietnam.aquaiget.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class LoginInfo{
    @JsonField(name = arrayOf("status"))
    var status: String? = null
    @JsonField(name = arrayOf("message"))
    var message: String? = null
    @JsonField(name = arrayOf("user"))
    var user: UserLogin? = UserLogin()
}
@JsonObject
class UserLogin {
    @JsonField(name = arrayOf("employeeId"))
    var employeeId: String? = ""
    @JsonField(name = arrayOf("accessToken"))
    var accessToken: String? = ""
}
