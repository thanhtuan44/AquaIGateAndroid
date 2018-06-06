package vn.aquavietnam.aquaiget.login

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.view_edt_with_border.view.*
import vn.aquavietnam.aquaiget.LoginBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseActivity
import vn.aquavietnam.aquaiget.base.TabbarActivity
import vn.aquavietnam.aquaiget.model.*
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.DataBus.Companion.GET_LOGIN_EVENT
import vn.aquavietnam.aquaiget.rxjava.RxBus


class UserLoginActivity : BaseActivity() {

    private var loginVM: LoginVM? = null
    private var loginDisposable: Disposable ? = null
    private var loginBinding: LoginBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DeviceToken",SharedPrefs.instance.get("DeviceToken",String::class.java))
        if (loginBinding == null) {
            loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        }
        loginVM = LoginVM()
        loginBinding!!.loginVM = loginVM
        loginBinding!!.edtUserName.imgIcon.setImageResource(R.drawable.iconaccount)
        loginBinding!!.edtPassword.imgIcon.setImageResource(R.drawable.iconlock)
        loginBinding!!.edtUserName.editText.setText("008452")
        loginBinding!!.edtPassword.editText.setText("123456")
        loginBinding!!.edtPassword.editText.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        loginBinding!!.btnSignIn.setOnClickListener{
            showLoading(loginBinding!!.cstLogin)
            loginVM!!.login(edtUserName.editText.getText().toString(),edtPassword.editText.getText().toString())
        }

    }

    override fun onResume() {
        super.onResume()
        AQUA_User.shared.getUser()
        if(AQUA_User.shared.userAqua.isLogin == true) {
            val intent : Intent = Intent(
                    this@UserLoginActivity,
                    TabbarActivity::class.java
            )
            startActivity(intent)
        }
        loginDisposable = RxBus.instance.observableWithEvent(GET_LOGIN_EVENT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        val login : LoginInfo = o.eventValue as LoginInfo
                        if(login.status == "1") {
                            val userData : UserLogin? = login.user
                            if(userData != null) {
                                if(userData.employeeId != null && userData.employeeId != "" && userData.accessToken != null && userData.accessToken != "") {
                                    val user = User(login.user?.employeeId.toString(),login.user?.accessToken.toString(),false,true)
                                    AQUA_User.shared.saveUser(user)
                                    Log.v("accessToken",AQUA_User.shared.userAqua.userToken)
                                    Log.v("employeeId",AQUA_User.shared.userAqua.userID)
                                    if(AQUA_User.shared.userAqua.userToken!! != "" && AQUA_User.shared.userAqua.userID!! != ""){
                                        this.registerDeviceToken(SharedPrefs.instance.get("DeviceToken",String::class.java))
                                        val intent : Intent = Intent(
                                                this@UserLoginActivity,
                                                TabbarActivity::class.java
                                        )
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                        startActivity(intent)
                                    }else {
                                        hideLoading(loginBinding!!.cstLogin)
                                        Toast.makeText(this, login.message,
                                                Toast.LENGTH_LONG).show();
                                    }
                                }else {
                                    hideLoading(loginBinding!!.cstLogin)
                                    Toast.makeText(this, login.message,
                                            Toast.LENGTH_LONG).show();
                                }
                            }else {
                                hideLoading(loginBinding!!.cstLogin)
                                Toast.makeText(this, login.message,
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                })

    }
    fun registerDeviceToken(token: String?) {
        if(token != null) {
            AquaService.registerDeviceTokenService()
                    .setDeviceToken(token)
                    .execute().subscribe({ data ->
                        if (data != null) {
                            if(data.status == "1") {
                                Log.d("DeviceToken Success",token)
                            }
                        }
                    }, { error ->
                        Log.v("UserLoginActivity", error.message)
                    })
        }

    }
    override fun onPause() {
        super.onPause()
        if(loginDisposable != null) {
            loginDisposable!!.dispose()
        }
    }
}