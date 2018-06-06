package vn.aquavietnam.aquaiget.homepage.workdatedetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_with_line.view.*
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.StringUtils
import vn.aquavietnam.aquaiget.WorkDateDetailSub1Binding
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.homepage.workdate.WorkDateFragment
import vn.aquavietnam.aquaiget.model.WorkDate

/**
 * Created by ThanhTuan on 5/7/2018.
 */
class WorkDateFragmentDetailSub1 : BaseBackFragment() {

    private var workdatedetailsub1Binding: WorkDateDetailSub1Binding? = null
    var data : WorkDate? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (workdatedetailsub1Binding == null) {
            workdatedetailsub1Binding = DataBindingUtil.inflate(inflater, R.layout.frag_workdate_detail_tab1, container, false)
        }
        return workdatedetailsub1Binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun findData() {
    }

    override fun backView() {
        getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right).replace(R.id.tabbarContent, WorkDateFragment(),"WorkDateFragment").addToBackStack("WorkDateFragment").commit()

    }
    override fun onResume() {
        super.onResume()

    }

    override fun callService() {

    }
    fun setDataOnView(workDate: WorkDate?){
        workdatedetailsub1Binding!!.vwlWorkDate.lblViewWithLineInfo.text = "Ngày làm việc"
        workdatedetailsub1Binding!!.vwlTimeIn.lblViewWithLineInfo.text = "Thời gian vào"
        workdatedetailsub1Binding!!.vwlTimeOut.lblViewWithLineInfo.text = "Thời gian ra"
        workdatedetailsub1Binding!!.vwlWorkJob.lblViewWithLineInfo.text = "Công việc"
        workdatedetailsub1Binding!!.vwlTotalTime.lblViewWithLineInfo.text = "Thời gian làm việc"

        if(workDate != null) {
            workdatedetailsub1Binding!!.vwlWorkDate.lblViewWithLineValue.text = workDate.workDate
            workdatedetailsub1Binding!!.vwlTimeIn.lblViewWithLineValue.text = StringUtils.convertDate(workDate.timeIn)
            workdatedetailsub1Binding!!.vwlTimeOut.lblViewWithLineValue.text = StringUtils.convertDate(workDate.timeOut)
            workdatedetailsub1Binding!!.vwlWorkJob.lblViewWithLineValue.text = "(Chưa có thông tin)"
            workdatedetailsub1Binding!!.vwlTotalTime.lblViewWithLineValue.text = workDate.totalWorkTime
        }
    }

    override fun onPause() {
        super.onPause()
    }
}