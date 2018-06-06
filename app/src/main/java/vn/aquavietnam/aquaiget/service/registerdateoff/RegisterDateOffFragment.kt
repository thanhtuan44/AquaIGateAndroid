package vn.aquavietnam.aquaiget.service.registerdateoff

import android.app.Fragment
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
import kotlinx.android.synthetic.main.view_with_line.view.*
import vn.aquavietnam.aquaiget.DateUtils
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.RegisterDateOffBinding
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.common.Utilities
import vn.aquavietnam.aquaiget.homepage.nonscan.NonScanFragment
import vn.aquavietnam.aquaiget.model.*
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus
import vn.aquavietnam.aquaiget.service.ServiceFragment
import vn.aquavietnam.aquaiget.service.customdialogfragment.DatePickerFragment
import vn.aquavietnam.aquaiget.service.customdialogfragment.ListDialogFragment
import vn.aquavietnam.aquaiget.service.customdialogfragment.TimePickerFragment
import java.util.*

/**
 * Created by ThanhTuan on 5/8/2018.
 */
class RegisterDateOffFragment : BaseBackFragment() {

    private var registerDateOffBinding: RegisterDateOffBinding? = null
    private var registerDateOffVM: RegisterDateOffVM? = null
    private var employeeID: String? = null
    private var managerID: String? = ""
    private var employeTypeID: String? = "1"
    private var reasonID: String? = "1"
    private var nonScanData : NonScan? = null
    var registerDateOffDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (registerDateOffBinding == null) {
            registerDateOffBinding = DataBindingUtil.inflate(inflater, R.layout.frag_register_dateoff, container, false)
        }
        return registerDateOffBinding!!.root
    }
    override fun findData() {
        nonScanData = arguments?.getParcelable("NONSCAN_DATA")
        showLoading(registerDateOffBinding!!.cstRegisterDateOff)
        registerDateOffVM = RegisterDateOffVM(context)
        registerDateOffBinding!!.registerDateOffVM = registerDateOffVM

    }
    fun setData(employeeInfo: EmployeeInfo) {
        registerDateOffBinding!!.vwEmployee.lblViewWithLineInfo.text = "Nhân viên"
        registerDateOffBinding!!.vwEmployeeId.lblViewWithLineInfo.text = "Mã nhân viên"
        registerDateOffBinding!!.vwEmployeeType.lblViewWithLineInfo.text = "Loại nhân viên"
        registerDateOffBinding!!.vwReason.lblViewWithLineInfo.text = "Lý do nghỉ"
        registerDateOffBinding!!.vwDateBegin.lblViewWithLineInfo.text = "Ngày bắt đâu"
        registerDateOffBinding!!.vwDateEnd.lblViewWithLineInfo.text = "Ngày kết thúc"
        registerDateOffBinding!!.vwTimeBegin.lblViewWithLineInfo.text = "Thời gian bắt đầu"
        registerDateOffBinding!!.vwTimeEnd.lblViewWithLineInfo.text = "Thời gian kết thúc"
        registerDateOffBinding!!.vwPersonReceive.lblViewWithLineInfo.text = "Người nhận"

        registerDateOffBinding!!.vwEmployee.lblViewWithLineValue.text = employeeInfo.fullName.toString()
        registerDateOffBinding!!.vwEmployeeId.lblViewWithLineValue.text = employeeInfo.employeeId.toString()
        registerDateOffBinding!!.vwEmployeeType.lblViewWithLineValue.text = "Chọn loại nhân viên"
        registerDateOffBinding!!.vwReason.lblViewWithLineValue.text = "Chọn lí do nghỉ"
        registerDateOffBinding!!.vwDateBegin.lblViewWithLineValue.text = DateUtils.getCurrentDate()
        registerDateOffBinding!!.vwDateEnd.lblViewWithLineValue.text = DateUtils.getCurrentDate()
        registerDateOffBinding!!.vwTimeBegin.lblViewWithLineValue.text = "07:30"
        registerDateOffBinding!!.vwTimeEnd.lblViewWithLineValue.text = "16:10"
        registerDateOffBinding!!.vwPersonReceive.lblViewWithLineValue.text = "Chọn người nhận"
    }

    override fun callService() {
        registerDateOffVM!!.getProfile()
        registerDateOffVM!!.showLoading = {
            showLoading(registerDateOffBinding!!.cstRegisterDateOff)
        }
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_PROFILE_EVENT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(registerDateOffBinding!!.cstRegisterDateOff)
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
        registerDateOffDisposable = RxBus.instance.observableWithEvent(DataBus.REGISTER_DAY_OFF)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(registerDateOffBinding!!.cstRegisterDateOff)
                        val data: PostDataResult = o.eventValue as PostDataResult
                        if (data != null) {
                            if (context != null) {
                                if(data.status == "1") {
                                    Utilities.showAlert(context,"Đã gửi đơn xin nghỉ thành công")
                                }else {
                                    Utilities.showAlert(context,data.message ?: "")
                                }
                            }
                        }
                    }
                })
        registerDateOffBinding!!.vwEmployeeType.btnWithLine.setOnClickListener {
            val dialogTypeEmployee = ListDialogFragment().newInstance(createListTypeNhanVien(),null,"Loại nhân viên")
            dialogTypeEmployee.show(this.getBaseActivity().fragmentManager,null)
            dialogTypeEmployee.dataFromDialog = {data ->
                registerDateOffBinding!!.vwEmployeeType.lblViewWithLineValue.text = data.valueData
                employeTypeID = data.keyData
            }
        }

        registerDateOffBinding!!.vwReason.btnWithLine.setOnClickListener {
            val dialogTypeReason = ListDialogFragment().newInstance(createListTypeReason(),null,"Lý do nghỉ")
            dialogTypeReason.show(this.getBaseActivity().fragmentManager,null)
            dialogTypeReason.dataFromDialog = {data ->
                registerDateOffBinding!!.vwReason.lblViewWithLineValue.text = data.valueData
                reasonID = data.keyData
            }
        }
        registerDateOffBinding!!.vwPersonReceive.btnWithLine.setOnClickListener {
            val dialogReceiver = ListDialogFragment().newInstance(null,employeeID,"Người nhận")
            dialogReceiver.show(this.getBaseActivity().fragmentManager,null)
            dialogReceiver.dataFromDialogManager = {data ->
                registerDateOffBinding!!.vwPersonReceive.lblViewWithLineValue.text = data.nameManager
                managerID = data.idManager
            }
        }
        registerDateOffBinding!!.vwTimeBegin.btnWithLine.setOnClickListener {
            val timeFragment = TimePickerFragment()
            timeFragment.show(this.getBaseActivity().fragmentManager,null)
            timeFragment.selectTime = { time ->
                registerDateOffBinding!!.vwTimeBegin.lblViewWithLineValue.text = time
            }
        }
        registerDateOffBinding!!.vwTimeEnd.btnWithLine.setOnClickListener {
            val timeFragment = TimePickerFragment()
            timeFragment.show(this.getBaseActivity().fragmentManager, null)
            timeFragment.selectTime = { time ->
                registerDateOffBinding!!.vwTimeEnd.lblViewWithLineValue.text = time
            }
        }
        registerDateOffBinding!!.vwDateBegin.btnWithLine.setOnClickListener {
            val dateFragment = DatePickerFragment()
            dateFragment.show(this.getBaseActivity().fragmentManager, null)
            dateFragment.selectDate = {date ->
                registerDateOffBinding!!.vwDateBegin.lblViewWithLineValue.text = date
            }
        }
        registerDateOffBinding!!.vwDateEnd.btnWithLine.setOnClickListener {
            val dateFragment = DatePickerFragment()
            dateFragment.show(this.getBaseActivity().fragmentManager, null)
            dateFragment.selectDate = {date ->
                registerDateOffBinding!!.vwDateEnd.lblViewWithLineValue.text = date
            }
        }
        registerDateOffBinding!!.btnRegisterDateOff.setOnClickListener {
            registerDateOffVM!!.registerDayOff(
                    context = context!!,
                    employeeType = registerDateOffBinding!!.vwEmployeeType.lblViewWithLineValue.text.toString(),
                    employeeId = employeTypeID!!.toInt(),
                    date = DateUtils.getCurrentDate(),
                    reason = registerDateOffBinding!!.vwReason.lblViewWithLineValue.text.toString(),
                    reasonId = reasonID!!.toInt(),
                    content = registerDateOffBinding!!.edtNote.text.toString(),
                    fromTime = registerDateOffBinding!!.vwTimeBegin.lblViewWithLineValue.text.toString(),
                    fromDate = registerDateOffBinding!!.vwDateBegin.lblViewWithLineValue.text.toString(),
                    toTime = registerDateOffBinding!!.vwTimeEnd.lblViewWithLineValue.text.toString(),
                    toDate = registerDateOffBinding!!.vwDateEnd.lblViewWithLineValue.text.toString(),
                    totalAbsentDays = "1",
                    decision = "",
                    requestBy = managerID ?: ""
            )
        }

    }
    private fun createListTypeNhanVien(): ArrayList<DataBase> {
        val arrData = ArrayList<DataBase>()
        arrData.add(DataBase("1","Nhân viên chính thức"))
        arrData.add(DataBase("2","Nhân viên thử việc"))
        return arrData
    }

    private fun createListTypeReason(): ArrayList<DataBase> {
        val arrData = ArrayList<DataBase>()
        arrData.add(DataBase("1","Vắng không phép"))
        arrData.add(DataBase("2","Nghỉ bù"))
        arrData.add(DataBase("3","Nghỉ chuẩn bị đổi việc"))
        arrData.add(DataBase("4","Nghỉ do tai nạn lao động"))
        arrData.add(DataBase("5","Nghỉ khám thai, hộ sản"))
        arrData.add(DataBase("6","Nghỉ bệnh (Có giấy hưởng BHXH)"))
        arrData.add(DataBase("7","Nghỉ bệnh (Không có giấy hưởng BHXH)"))
        arrData.add(DataBase("8","Nghỉ việc riêng không lương"))
        arrData.add(DataBase("9","Nghỉ bệnh quá thời hạn quy định"))
        arrData.add(DataBase("10","Nghỉ việc riêng có lương (kết hôn, ma chay,...)"))
        arrData.add(DataBase("11","Nghỉ phép"))
        arrData.add(DataBase("12","Lý do khác"))
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