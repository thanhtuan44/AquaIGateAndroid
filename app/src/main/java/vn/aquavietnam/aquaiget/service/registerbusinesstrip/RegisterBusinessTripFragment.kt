package vn.aquavietnam.aquaiget.service.registerbusinesstrip

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
import vn.aquavietnam.aquaiget.RegisterBusinessTripBinding
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.homepage.nonscan.NonScanFragment
import vn.aquavietnam.aquaiget.model.*
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus
import vn.aquavietnam.aquaiget.service.ServiceFragment
import vn.aquavietnam.aquaiget.service.customdialogfragment.DatePickerFragment
import vn.aquavietnam.aquaiget.service.customdialogfragment.ListDialogFragment
import vn.aquavietnam.aquaiget.service.customdialogfragment.TimePickerFragment
import vn.aquavietnam.aquaiget.service.registerbusinesstrip.RegisterBusinessTripVM
import java.util.ArrayList

/**
 * Created by ThanhTuan on 5/31/2018.
 */
class RegisterBusinessTripFragment : BaseBackFragment() {

    private var registerBusinessTripBinding: RegisterBusinessTripBinding? = null
    private var registerBusinessTripVM: RegisterBusinessTripVM? = null
    private var employeeID: String? = null
    private var managerID: String? = ""
    private var employeTypeID: String? = "1"
    private var reasonID: String? = "1"
    private var nonScanData : NonScan? = null
    var registerBusinessTripDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (registerBusinessTripBinding == null) {
            registerBusinessTripBinding = DataBindingUtil.inflate(inflater, R.layout.frag_register_business_trip, container, false)
        }
        return registerBusinessTripBinding!!.root
    }
    override fun findData() {
        nonScanData = arguments?.getParcelable("NONSCAN_DATA")
        showLoading(registerBusinessTripBinding!!.cstRegisterBusinessTrip)
        registerBusinessTripVM = RegisterBusinessTripVM(context)
        registerBusinessTripBinding!!.businessTripVM = registerBusinessTripVM

    }
    fun setData(employeeInfo: EmployeeInfo) {
        registerBusinessTripBinding!!.vwEmployee.lblViewWithLineInfo.text = "Nhân viên"
        registerBusinessTripBinding!!.vwEmployeeId.lblViewWithLineInfo.text = "Mã nhân viên"
        registerBusinessTripBinding!!.vwTimeBegin.lblViewWithLineInfo.text = "Thời gian bắt đầu"
        registerBusinessTripBinding!!.vwTimeEnd.lblViewWithLineInfo.text = "Thời gian kết thúc"
        registerBusinessTripBinding!!.vwPersonReceive.lblViewWithLineInfo.text = "Người nhận"

        registerBusinessTripBinding!!.vwEmployee.lblViewWithLineValue.text = employeeInfo.fullName.toString()
        registerBusinessTripBinding!!.vwEmployeeId.lblViewWithLineValue.text = employeeInfo.employeeId.toString()
        registerBusinessTripBinding!!.vwTimeBegin.lblViewWithLineValue.text = "07:30"
        registerBusinessTripBinding!!.vwTimeEnd.lblViewWithLineValue.text = "16:10"
        registerBusinessTripBinding!!.vwPersonReceive.lblViewWithLineValue.text = "Chọn người nhận"
    }

    override fun callService() {
        registerBusinessTripVM!!.getProfile()
        registerBusinessTripVM!!.showLoading = {
            showLoading(registerBusinessTripBinding!!.cstRegisterBusinessTrip)
        }
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_PROFILE_EVENT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(registerBusinessTripBinding!!.cstRegisterBusinessTrip)
                        val employeeInfo: Employee = o.eventValue as Employee
                        Log.d("EMPLOYEE INFO",employeeInfo.toString())
                        if (employeeInfo != null) {
                            if (context != null) {
                                if(employeeInfo.user != null) {
                                    setData(employeeInfo.user!!)
                                    employeeID = employeeInfo.user!!.employeeId.toString()
                                }
                            }
                        }
                    }
                })
        registerBusinessTripDisposable = RxBus.instance.observableWithEvent(DataBus.REGISTER_DAY_OFF)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(registerBusinessTripBinding!!.cstRegisterBusinessTrip)
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
        registerBusinessTripBinding!!.vwPersonReceive.btnWithLine.setOnClickListener {
            val dialogReceiver = ListDialogFragment().newInstance(null,employeeID,"Người nhận")
            dialogReceiver.show(this.getBaseActivity().fragmentManager,null)
            dialogReceiver.dataFromDialogManager = {data ->
                registerBusinessTripBinding!!.vwPersonReceive.lblViewWithLineValue.text = data.nameManager
                managerID = data.idManager
            }
        }
        registerBusinessTripBinding!!.vwTimeBegin.btnWithLine.setOnClickListener {
            val timeFragment = TimePickerFragment()
            timeFragment.show(this.getBaseActivity().fragmentManager,null)
            timeFragment.selectTime = { time ->
                registerBusinessTripBinding!!.vwTimeBegin.lblViewWithLineValue.text = time
            }
        }
        registerBusinessTripBinding!!.vwTimeEnd.btnWithLine.setOnClickListener {
            val timeFragment = TimePickerFragment()
            timeFragment.show(this.getBaseActivity().fragmentManager, null)
            timeFragment.selectTime = { time ->
                registerBusinessTripBinding!!.vwTimeEnd.lblViewWithLineValue.text = time
            }
        }

        registerBusinessTripBinding!!.btnRegisterBusinessTrip.setOnClickListener {
//            registerBusinessTripVM!!.registerDayOff(
//                    employeeId = employeTypeID!!.toInt(),
//                    date = DateUtils.getCurrentDate(),
//                    reasonId = reasonID!!.toInt(),
//                    content = registerBusinessTripBinding!!.edtNote.text.toString(),
//                    fromTime = registerBusinessTripBinding!!.vwTimeBegin.lblViewWithLineValue.text.toString(),
//                    toTime = registerBusinessTripBinding!!.vwTimeEnd.lblViewWithLineValue.text.toString(),
//                    totalAbsentDays = "1",
//                    decision = "",
//                    requestBy = managerID ?: ""
//            )
        }

    }


    override fun onPause() {
        super.onPause()
    }
    override fun backView() {
        if(nonScanData == null) {
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.tabbarContent, ServiceFragment(),"ServiceFragment").addToBackStack("ServiceFragment").commit()
        }else{
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.tabbarContent, NonScanFragment(),"NonScanFragment").addToBackStack("NonScanFragment").commit()
        }
    }
}