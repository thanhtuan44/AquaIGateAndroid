package vn.aquavietnam.aquaiget.service.registernonscan

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.view_with_line.view.*
import vn.aquavietnam.aquaiget.DateUtils
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.RegisterNonScanBinding
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.homepage.nonscan.NonScanFragment
import vn.aquavietnam.aquaiget.model.Employee
import vn.aquavietnam.aquaiget.model.EmployeeInfo
import vn.aquavietnam.aquaiget.model.NonScan
import vn.aquavietnam.aquaiget.model.PostDataResult
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus
import vn.aquavietnam.aquaiget.service.ServiceFragment
import vn.aquavietnam.aquaiget.service.customdialogfragment.DatePickerFragment
import vn.aquavietnam.aquaiget.service.customdialogfragment.ListDialogFragment
import vn.aquavietnam.aquaiget.service.customdialogfragment.TimePickerFragment

/**
 * Created by ThanhTuan on 5/9/2018.
 */
class RegisterNonScanFragment : BaseBackFragment() {

    private var registerNonScanBinding: RegisterNonScanBinding? = null
    private var registerNonScanVM: RegisterNonScanVM? = null
    private var employeeID: String? = null
    private var managerID: String? = ""
    var registerNonScanDisposable: Disposable? = null
    private var nonScanData : NonScan? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (registerNonScanBinding == null) {
            registerNonScanBinding = DataBindingUtil.inflate(inflater, R.layout.frag_register_nonscan, container, false)
        }
        return registerNonScanBinding!!.root
    }
    override fun findData() {
        nonScanData = arguments?.getParcelable("NONSCAN_DATA")
        showLoading(registerNonScanBinding!!.cstRegisterNonScan)
        registerNonScanVM = RegisterNonScanVM(context)
        registerNonScanBinding!!.registerNonScanVM = registerNonScanVM

    }
    fun setData(employeeInfo: EmployeeInfo) {
        registerNonScanBinding!!.vwEmployee.lblViewWithLineInfo.text = "Nhân viên"
        registerNonScanBinding!!.vwEmployeeId.lblViewWithLineInfo.text = "Mã nhân viên"
        registerNonScanBinding!!.vwDate.lblViewWithLineInfo.text = "Ngày quên quẹt thẻ"
        registerNonScanBinding!!.vwTimeBegin.lblViewWithLineInfo.text = "Thời gian bắt đầu"
        registerNonScanBinding!!.vwTimeEnd.lblViewWithLineInfo.text = "Thời gian kết thúc"
        registerNonScanBinding!!.vwPersonReceive.lblViewWithLineInfo.text = "Người nhận"

        registerNonScanBinding!!.vwEmployee.lblViewWithLineValue.text = employeeInfo.fullName.toString()
        registerNonScanBinding!!.vwEmployeeId.lblViewWithLineValue.text = employeeInfo.employeeId.toString()
        registerNonScanBinding!!.vwDate.lblViewWithLineValue.text = DateUtils.getCurrentDate()
        registerNonScanBinding!!.vwTimeBegin.lblViewWithLineValue.text = "07:30"
        registerNonScanBinding!!.vwTimeEnd.lblViewWithLineValue.text = "16:10"
        registerNonScanBinding!!.vwPersonReceive.lblViewWithLineValue.text = "Chọn người nhận"
    }

    override fun callService() {
        registerNonScanVM!!.getProfile()
        registerNonScanVM!!.showLoading = {
            showLoading(registerNonScanBinding!!.cstRegisterNonScan)
        }
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_PROFILE_EVENT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(registerNonScanBinding!!.cstRegisterNonScan)
                        val employeeInfo: Employee = o.eventValue as Employee
                        Log.d("EMPLOYEE INFO",employeeInfo.toString())
                        if (employeeInfo != null) {
                            if (context != null) {
                                setData(employeeInfo.user!!)
                                employeeID = employeeInfo.user!!.employeeId.toString()
                            }
                        }
                    }
                })
        registerNonScanDisposable = RxBus.instance.observableWithEvent(DataBus.REGISTER_NONSCAN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(registerNonScanBinding!!.cstRegisterNonScan)
                        val data: PostDataResult = o.eventValue as PostDataResult
                        if (data != null) {
                            if (context != null) {
                                if(data.status == "1") {
                                    Toast.makeText(this.context,"Đã gửi đơn xin nghỉ thành công", Toast.LENGTH_LONG).show()
                                }else {
                                    Toast.makeText(this.context,data.message ?: "", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                })

        registerNonScanBinding!!.vwPersonReceive.btnWithLine.setOnClickListener {
            val dialogReceiver = ListDialogFragment().newInstance(null,employeeID,"Người nhận")
            dialogReceiver.show(this.getBaseActivity().fragmentManager,null)
            dialogReceiver.dataFromDialogManager = {data ->
                registerNonScanBinding!!.vwPersonReceive.lblViewWithLineValue.text = data.nameManager
                managerID = data.idManager
            }
        }
        registerNonScanBinding!!.vwTimeBegin.btnWithLine.setOnClickListener {
            val timeFragment = TimePickerFragment()
            timeFragment.show(this.getBaseActivity().fragmentManager,null)
            timeFragment.selectTime = { time ->
                registerNonScanBinding!!.vwTimeBegin.lblViewWithLineValue.text = time
            }
        }
        registerNonScanBinding!!.vwTimeEnd.btnWithLine.setOnClickListener {
            val timeFragment = TimePickerFragment()
            timeFragment.show(this.getBaseActivity().fragmentManager, null)
            timeFragment.selectTime = { time ->
                registerNonScanBinding!!.vwTimeEnd.lblViewWithLineValue.text = time
            }
        }
        registerNonScanBinding!!.vwDate.btnWithLine.setOnClickListener {
            val dateFragment = DatePickerFragment()
            dateFragment.show(this.getBaseActivity().fragmentManager, null)
            dateFragment.selectDate = {date ->
                registerNonScanBinding!!.vwDate.lblViewWithLineValue.text = date
            }
        }
        registerNonScanBinding!!.btnRegisterNonScan.setOnClickListener {
            registerNonScanVM!!.registerNonScan(
                    date = DateUtils.getCurrentDate(),
                    reason = registerNonScanBinding!!.edtNote.text.toString(),
                    timeIn = registerNonScanBinding!!.vwTimeBegin.lblViewWithLineValue.text.toString(),
                    timeOut = registerNonScanBinding!!.vwTimeEnd.lblViewWithLineValue.text.toString(),
                    requestBy = managerID ?: ""
            )
        }
    }

    override fun onPause() {
        super.onPause()
    }
    override fun backView() {
        if(nonScanData == null) {
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right).replace(R.id.tabbarContent, ServiceFragment(),"ServiceFragment").addToBackStack("ServiceFragment").commit()
        }else{
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right).replace(R.id.tabbarContent, NonScanFragment(),"NonScanFragment").addToBackStack("NonScanFragment").commit()
        }
    }
}