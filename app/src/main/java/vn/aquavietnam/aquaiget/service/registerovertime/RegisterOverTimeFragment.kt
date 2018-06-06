package vn.aquavietnam.aquaiget.service.registerovertime

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
import kotlinx.android.synthetic.main.frag_register_dateoff.*
import kotlinx.android.synthetic.main.frag_register_overtime.*
import kotlinx.android.synthetic.main.view_with_line.view.*
import vn.aquavietnam.aquaiget.DateUtils
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.RegisterOverTimeBinding
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.homepage.nonscan.NonScanFragment
import vn.aquavietnam.aquaiget.model.*
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus
import vn.aquavietnam.aquaiget.service.ServiceFragment
import vn.aquavietnam.aquaiget.service.customdialogfragment.DatePickerFragment
import vn.aquavietnam.aquaiget.service.customdialogfragment.ListDialogFragment
import vn.aquavietnam.aquaiget.service.customdialogfragment.TimePickerFragment
import java.util.ArrayList

/**
 * Created by ThanhTuan on 5/9/2018.
 */
class RegisterOverTimeFragment: BaseBackFragment() {
    private var registerOverTimeBinding: RegisterOverTimeBinding? = null
    private var registerOverTimeVM: RegisterOverTimeVM? = null
    private var employeeID: String? = null
    private var managerID: String? = ""
    private var overtimeTypeID: String? = "1"
    var registerOvertimeDisposable: Disposable? = null
    private var nonScanData : NonScan? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (registerOverTimeBinding == null) {
            registerOverTimeBinding = DataBindingUtil.inflate(inflater, R.layout.frag_register_overtime, container, false)
        }
        return registerOverTimeBinding!!.root
    }
    override fun findData() {
        nonScanData = arguments?.getParcelable("NONSCAN_DATA")
        showLoading(registerOverTimeBinding!!.cstRegisterOvertime)
        registerOverTimeVM = RegisterOverTimeVM(context)
        registerOverTimeBinding!!.registerOverTimeVM = registerOverTimeVM

    }
    fun setData(employeeInfo: EmployeeInfo) {
        registerOverTimeBinding!!.vwEmployee.lblViewWithLineInfo.text = "Nhân viên"
        registerOverTimeBinding!!.vwEmployeeId.lblViewWithLineInfo.text = "Mã nhân viên"
        registerOverTimeBinding!!.vwOverTimeType.lblViewWithLineInfo.text = "Loại tăng ca"
        registerOverTimeBinding!!.vwDate.lblViewWithLineInfo.text = "Ngày tăng ca"
        registerOverTimeBinding!!.vwTimeBegin.lblViewWithLineInfo.text = "Thời gian bắt đầu"
        registerOverTimeBinding!!.vwTimeEnd.lblViewWithLineInfo.text = "Thời gian kết thúc"
        registerOverTimeBinding!!.vwPersonReceive.lblViewWithLineInfo.text = "Người nhận"

        registerOverTimeBinding!!.vwEmployee.lblViewWithLineValue.text = employeeInfo.fullName.toString()
        registerOverTimeBinding!!.vwEmployeeId.lblViewWithLineValue.text = employeeInfo.employeeId.toString()
        registerOverTimeBinding!!.vwOverTimeType.lblViewWithLineValue.text = "Chọn loại tăng ca"
        registerOverTimeBinding!!.vwDate.lblViewWithLineValue.text = DateUtils.getCurrentDate()
        registerOverTimeBinding!!.vwTimeBegin.lblViewWithLineValue.text = "07:30"
        registerOverTimeBinding!!.vwTimeEnd.lblViewWithLineValue.text = "16:10"
        registerOverTimeBinding!!.vwPersonReceive.lblViewWithLineValue.text = "Chọn người nhận"
    }

    override fun callService() {
        registerOverTimeVM!!.getProfile()
        registerOverTimeVM!!.showLoading = {
            showLoading(registerOverTimeBinding!!.cstRegisterOvertime)
        }
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_PROFILE_EVENT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(registerOverTimeBinding!!.cstRegisterOvertime)
                        val employeeInfo: Employee = o.eventValue as Employee
                        Log.d("EMPLOYEE INFO",employeeInfo.toString())
                        if (employeeInfo != null) {
                            if (context != null) {
                                setData(employeeInfo.user!!)
                                employeeID = employeeInfo.user!!.employeeId!!.toString()
                            }
                        }
                    }
                })
        registerOvertimeDisposable = RxBus.instance.observableWithEvent(DataBus.REGISTER_OVERTIME)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(registerOverTimeBinding!!.cstRegisterOvertime)
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
        registerOverTimeBinding!!.vwOverTimeType.btnWithLine.setOnClickListener {
            val dialogTypeEmployee = ListDialogFragment().newInstance(createListTypeOvertime(),null,"Loại tăng ca")
            dialogTypeEmployee.show(this.getBaseActivity().fragmentManager,null)
            dialogTypeEmployee.dataFromDialog = {data ->
                registerOverTimeBinding!!.vwOverTimeType.lblViewWithLineValue.text = data.valueData
                overtimeTypeID = data.keyData
            }
        }
        registerOverTimeBinding!!.vwPersonReceive.btnWithLine.setOnClickListener {
            val dialogReceiver = ListDialogFragment().newInstance(null,employeeID,"Người nhận")
            dialogReceiver.show(this.getBaseActivity().fragmentManager,null)
            dialogReceiver.dataFromDialogManager = {data ->
                registerOverTimeBinding!!.vwPersonReceive.lblViewWithLineValue.text = data.nameManager
                managerID = data.idManager
            }
        }
        registerOverTimeBinding!!.vwTimeBegin.btnWithLine.setOnClickListener {
            val timeFragment = TimePickerFragment()
            timeFragment.show(this.getBaseActivity().fragmentManager,null)
            timeFragment.selectTime = { time ->
                registerOverTimeBinding!!.vwTimeBegin.lblViewWithLineValue.text = time
            }
        }
        registerOverTimeBinding!!.vwTimeEnd.btnWithLine.setOnClickListener {
            val timeFragment = TimePickerFragment()
            timeFragment.show(this.getBaseActivity().fragmentManager, null)
            timeFragment.selectTime = { time ->
                registerOverTimeBinding!!.vwTimeEnd.lblViewWithLineValue.text = time
            }
        }
        registerOverTimeBinding!!.vwDate.btnWithLine.setOnClickListener {
            val dateFragment = DatePickerFragment()
            dateFragment.show(this.getBaseActivity().fragmentManager, null)
            dateFragment.selectDate = {date ->
                registerOverTimeBinding!!.vwDate.lblViewWithLineValue.text = date
            }
        }
        registerOverTimeBinding!!.btnRegisterOverTime.setOnClickListener {
            registerOverTimeVM!!.registerOvertime(
                    overtimeTypeId = overtimeTypeID!!.toInt(),
                    overtimeType = registerOverTimeBinding!!.vwOverTimeType.lblViewWithLineValue.text.toString(),
                    date = DateUtils.getCurrentDate(),
                    fromTime = registerOverTimeBinding!!.vwTimeBegin.lblViewWithLineValue.text.toString(),
                    toTime = registerOverTimeBinding!!.vwTimeEnd.lblViewWithLineValue.text.toString(),
                    requestBy = managerID ?: "",
                    purpose = registerOverTimeBinding!!.txvNote.text.toString()
            )
        }
    }
    private fun createListTypeOvertime(): ArrayList<DataBase> {
        val arrData = ArrayList<DataBase>()
        arrData.add(DataBase("1","Day-shift of normal day"))
        arrData.add(DataBase("2","Night-shift of normal day"))
        arrData.add(DataBase("3","Overtime with compensated day"))
        arrData.add(DataBase("4","Day-shift of holiday"))
        arrData.add(DataBase("5","Night-shift of holiday"))
        return arrData
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