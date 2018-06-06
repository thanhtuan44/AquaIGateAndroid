package vn.aquavietnam.aquaiget.homepage.workdatedetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_with_line.view.*
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.WorkDateDetailSub2Binding
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.homepage.workdate.WorkDateFragment
import vn.aquavietnam.aquaiget.model.WorkDate

/**
 * Created by ThanhTuan on 5/7/2018.
 */
class WorkDateFragmentDetailSub2 : BaseBackFragment() {

    private var workdatedetailsub2Binding: WorkDateDetailSub2Binding? = null
    var data : WorkDate? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (workdatedetailsub2Binding == null) {
            workdatedetailsub2Binding = DataBindingUtil.inflate(inflater, R.layout.frag_workdate_detail_tab2, container, false)
        }
        return workdatedetailsub2Binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun findData() {
        setDataOnView(null)
    }
    override fun backView() {
        getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right).replace(R.id.tabbarContent, WorkDateFragment(),"WorkDateFragment").addToBackStack("WorkDateFragment").commit()
    }

    override fun callService() {

    }
    fun setDataOnView(workDate: WorkDate?){
        workdatedetailsub2Binding!!.vwlNS.lblViewWithLineInfo.text = "NS"
        workdatedetailsub2Binding!!.vwlNS20.lblViewWithLineInfo.text = "NS 2.0"
        workdatedetailsub2Binding!!.vwlNS15.lblViewWithLineInfo.text = "Tăng ca 1.5"
        workdatedetailsub2Binding!!.vwlOTM.lblViewWithLineInfo.text = "Tăng ca sáng"
        workdatedetailsub2Binding!!.vwlOT2.lblViewWithLineInfo.text = "Tăng ca 2.0"
        workdatedetailsub2Binding!!.vwlLT.lblViewWithLineInfo.text = "Đi trễ"
        workdatedetailsub2Binding!!.vwlET.lblViewWithLineInfo.text = "Về sớm"
        workdatedetailsub2Binding!!.vwlUT.lblViewWithLineInfo.text = "UT"
        workdatedetailsub2Binding!!.vwlMeal.lblViewWithLineInfo.text = "Meal"

        if(workDate != null) {
            workdatedetailsub2Binding!!.vwlNS.lblViewWithLineValue.text = workDate.ns
            workdatedetailsub2Binding!!.vwlNS20.lblViewWithLineValue.text = workDate.ns20
            workdatedetailsub2Binding!!.vwlNS15.lblViewWithLineValue.text = workDate.ot15
            workdatedetailsub2Binding!!.vwlOTM.lblViewWithLineValue.text = workDate.otm
            workdatedetailsub2Binding!!.vwlOT2.lblViewWithLineValue.text = workDate.ot20
            workdatedetailsub2Binding!!.vwlLT.lblViewWithLineValue.text = workDate.lt
            workdatedetailsub2Binding!!.vwlET.lblViewWithLineValue.text = workDate.et
            workdatedetailsub2Binding!!.vwlUT.lblViewWithLineValue.text = workDate.ut
            workdatedetailsub2Binding!!.vwlMeal.lblViewWithLineValue.text = workDate.meal
        }
    }
    override fun onResume() {
        super.onResume()

    }
}