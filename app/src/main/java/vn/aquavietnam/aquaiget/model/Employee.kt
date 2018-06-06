package vn.aquavietnam.aquaiget.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class Employee  {
    @JsonField(name = arrayOf("status"))
    var status: String? = ""
    @JsonField(name = arrayOf("message"))
    var message: String? = ""
    @JsonField(name = arrayOf("user"))
    var user: EmployeeInfo? = EmployeeInfo()
}
@JsonObject
class EmployeeInfo : BaseModel() {
    @JsonField(name = arrayOf("employeeId"))
    var employeeId: String? = ""
    @JsonField(name = arrayOf("fullName"))
    var fullName: String? = ""
    @JsonField(name = arrayOf("email"))
    var email: String? = ""
    @JsonField(name = arrayOf("phone"))
    var phone: String? = ""
    @JsonField(name = arrayOf("gender"))
    var gender: String? = ""
    @JsonField(name = arrayOf("birthday"))
    var birthday: String? = ""
    @JsonField(name = arrayOf("birthplace"))
    var birthplace: String? = ""
    @JsonField(name = arrayOf("education"))
    var education: String? = ""
    @JsonField(name = arrayOf("marital"))
    var marital: String? = ""
    @JsonField(name = arrayOf("religion"))
    var religion: String? = ""
    @JsonField(name = arrayOf("address"))
    var address: String? = ""
    @JsonField(name = arrayOf("company"))
    var company: String? = ""
    @JsonField(name = arrayOf("location"))
    var location: String? = ""
    @JsonField(name = arrayOf("department"))
    var department: String? = ""
    @JsonField(name = arrayOf("section"))
    var section: String? = ""
    @JsonField(name = arrayOf("posId"))
    var posId: String? = ""
    @JsonField(name = arrayOf("position"))
    var position: String? = ""
    @JsonField(name = arrayOf("joinDate"))
    var joinDate: String? = ""
    @JsonField(name = arrayOf("avatar"))
    var avatar: String? = ""
}