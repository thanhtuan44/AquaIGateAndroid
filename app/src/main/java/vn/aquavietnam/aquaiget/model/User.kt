package vn.aquavietnam.aquaiget.model

public class AQUA_User private constructor() {
    var userAqua : User = User("","",false,false)
    init { userAqua = User("","",false,false) }

    private object Holder { val INSTANCE = AQUA_User() }

    companion object {
        val shared: AQUA_User by lazy { Holder.INSTANCE }
    }
    fun saveUser(user: User?) {
        if(user != null) {
            SharedPrefs.instance.put("SaveUser",user)
            getUser()
        }
    }
    fun getUser() {
        var userTemp = SharedPrefs.instance.get("SaveUser",User::class.java)
        if(userTemp != null){
            this.userAqua = userTemp
        }else {
            this.userAqua = User("","",false,false)
        }
    }
    fun logoutUser() {
        SharedPrefs.instance.clear()
        val user = User("","",false,false)
        this.saveUser(user)
    }
}

 class User {
     var userID : String = ""
     var userToken : String = ""
     var isRememberID : Boolean = false
     var isLogin : Boolean = false

    constructor(userID : String, userToken: String, isRememberID: Boolean, isLogin: Boolean) {
        this.userID = userID
        this.userToken = userToken
        this.isRememberID = isRememberID
        this.isLogin = isLogin
    }
}