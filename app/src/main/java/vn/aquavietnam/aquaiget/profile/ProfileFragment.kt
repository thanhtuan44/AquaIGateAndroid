package vn.aquavietnam.aquaiget.profile

import android.app.AlertDialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.view_button_arrow.view.*
import kotlinx.android.synthetic.main.frag_profile.*
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseFragment
import vn.aquavietnam.aquaiget.model.*
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus
import vn.aquavietnam.aquaiget.login.UserLoginActivity
import com.bumptech.glide.Glide
import android.support.constraint.ConstraintSet
import android.util.Log
import io.reactivex.disposables.Disposable
import vn.aquavietnam.aquaiget.ProfileBinding
import vn.aquavietnam.aquaiget.profile.confirmdateoff.ConfirmDateOffFragment
import vn.aquavietnam.aquaiget.profile.confirmnonscan.ConfirmNonScanFragment
import vn.aquavietnam.aquaiget.profile.confirmovertime.ConfirmOvertimeFragment
import vn.aquavietnam.aquaiget.profile.listregisterdateoff.ListRegisterDateOffFragment
import vn.aquavietnam.aquaiget.profile.listregisternonscan.ListRegisterNonScanFragment
import vn.aquavietnam.aquaiget.profile.listregisterovertime.ListRegisterOvertimeFragment


/**
 * Created by ThanhTuan on 3/22/2018.
 * Modified by TrungHau on 04/26/2018.
 */
class ProfileFragment : BaseFragment() {

    private var profileBinding: ProfileBinding? = null
    private var profileVM: ProfileVM? = null
    var logoutDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (profileBinding == null) {
            profileBinding = DataBindingUtil.inflate(inflater, R.layout.frag_profile, container, false)
        }
        return profileBinding!!.root
    }
    override fun findData() {
        showLoading(profileBinding!!.cstProfile)
        profileVM = ProfileVM(context)
        profileVM!!.getProfile()
        profileBinding!!.btnRegisterDateOff.txvInfo.text = "Đăng kí nghỉ phép"
        profileBinding!!.btnRegisterOverTime.txvInfo.text = "Đăng kí tăng ca"
        profileBinding!!.btnConfirmScan.txvInfo.text = "Xác nhận quẹt thẻ"
        profileBinding!!.btnInfoAccount.txvInfo.text = "Chỉnh sửa tài khoản"
        profileBinding!!.btnConfirmDateOff.txvInfo.text = "Xác nhận nghỉ phép"
        profileBinding!!.btnConfirmOvertime.txvInfo.text = "Xác nhận tăng ca"
        profileBinding!!.btnConfirmForgotScan.txvInfo.text = "Xác nhận quên quẹt thẻ"
        profileBinding!!.btnConfirmScan.txvLine.setBackgroundResource(R.color.colorBackGround)
        profileBinding!!.profileVM = profileVM
        setAction()

    }

    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_PROFILE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(profileBinding!!.cstProfile)
                        val employee: Employee = o.eventValue as Employee
                        txtFullName?.setText(employee.user!!.fullName!!)
                        if(isAdded){
                            Glide.with(context)
                                    .load(employee.user!!.avatar!!)
                                    .into(imgAvatar)
                        }
                        if(employee.user!!.posId == "MA_" || employee.user!!.posId == "SM_") {
                            updateConstraint(true)
                        }else {
                            updateConstraint(false)
                        }
                        txtEmail?.setText("Email : " + employee.user!!.email!!)
                    }
                })
        logoutDisposable = RxBus.instance.observableWithEvent(DataBus.GET_LOGOUT_EVENT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(profileBinding!!.cstProfile)
                        val result: PostDataResult = o.eventValue as PostDataResult
                        if(result.status == "1"){
                            AQUA_User.shared.logoutUser()
                            val intent : Intent = Intent(getBaseActivity(), UserLoginActivity::class.java)
                            startActivity(intent)
                        }
                    }
                })
    }
    fun updateConstraint(isShow: Boolean) {

        val constraintSet = ConstraintSet()
        constraintSet.clone(profileBinding!!.cstInProfile)
        if(isShow) {
            constraintSet.connect(profileBinding!!.vwInfoAccount.id, ConstraintSet.TOP, profileBinding!!.btnConfirmForgotScan.id, ConstraintSet.BOTTOM)
            constraintSet.applyTo(profileBinding!!.cstInProfile)
        }else{
            constraintSet.connect(profileBinding!!.vwInfoAccount.id, ConstraintSet.TOP, profileBinding!!.btnConfirmScan.id, ConstraintSet.BOTTOM)
            constraintSet.applyTo(profileBinding!!.cstInProfile)
        }
        if(isShow) {
            profileBinding!!.grpXetDuyet.visibility = View.GONE
            profileBinding!!.grpXetDuyet.visibility = View.VISIBLE

        }else{
            profileBinding!!.grpXetDuyet.visibility = View.GONE
            profileBinding!!.grpXetDuyet.visibility = View.INVISIBLE
        }
    }

    override fun onPause() {
        super.onPause()
        if(logoutDisposable != null) {
            logoutDisposable!!.dispose()
        }
    }
    fun setAction() {
        profileBinding!!.btnRegisterDateOff.setOnClickListener {
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, ListRegisterDateOffFragment(),"ListRegisterDateOffFragment").addToBackStack("ListRegisterDateOffFragment").commit()
        }
        profileBinding!!.btnRegisterOverTime.setOnClickListener {
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, ListRegisterOvertimeFragment(),"ListRegisterOvertimeFragment").addToBackStack("ListRegisterOvertimeFragment").commit()
        }
        profileBinding!!.btnConfirmScan.setOnClickListener {
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, ListRegisterNonScanFragment(),"ListRegisterNonScanFragment").addToBackStack("ListRegisterNonScanFragment").commit()
        }
        profileBinding!!.btnConfirmDateOff.setOnClickListener {
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, ConfirmDateOffFragment(),"ConfirmDateOffFragment").addToBackStack("ConfirmDateOffFragment").commit()
        }
        profileBinding!!.btnConfirmOvertime.setOnClickListener {
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, ConfirmOvertimeFragment(),"ConfirmOvertimeFragment").addToBackStack("ConfirmOvertimeFragment").commit()
        }
        profileBinding!!.btnConfirmForgotScan.setOnClickListener {
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, ConfirmNonScanFragment(),"ConfirmNonScanFragment").addToBackStack("ConfirmNonScanFragment").commit()
        }
        profileBinding!!.btnSignOut.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Bạn có muốn đăng xuất?")
            builder.setPositiveButton("OK"){dialog, which ->
                showLoading(profileBinding!!.cstProfile)
                profileVM!!.logout()
                Log.d("","")
            }
            builder.setNegativeButton("Cancel"){dialog,which ->

            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}