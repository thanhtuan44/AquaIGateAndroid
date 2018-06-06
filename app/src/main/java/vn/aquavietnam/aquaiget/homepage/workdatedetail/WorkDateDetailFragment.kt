package vn.aquavietnam.aquaiget.homepage.workdatedetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.WorkDateDetailBinding
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.model.WorkDate
import vn.aquavietnam.aquaiget.model.WorkDateDetail
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/7/2018.
 */
class WorkDateDetailFragment : BaseBackFragment() {

    private var workdatedetailBinding: WorkDateDetailBinding? = null
    private var workdateDetailVM: WorkDateDetailVM? = null
    private var workdateSelected : String? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (workdatedetailBinding == null) {
            workdatedetailBinding = DataBindingUtil.inflate(inflater, R.layout.frag_workdate_detail, container, false)
        }
        return workdatedetailBinding!!.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun findData() {
        workdateSelected = arguments?.getString("BUNDLE_WORKDATE_SELECTED")
        workdatedetailBinding!!.recyclerTabLayout.setupWithViewPager(workdatedetailBinding!!.viewPager)
        workdateDetailVM = WorkDateDetailVM(context)

        workdatedetailBinding!!.workdatedetailVM = workdateDetailVM
    }
    override fun callService() {
        showLoading(workdatedetailBinding!!.cstWorkDateDetail)
        workdateDetailVM!!.getWorkDateDetail(workdateSelected!!)
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_WORKDATE_DETAIL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(workdatedetailBinding!!.cstWorkDateDetail)
                        val workdateDetail: WorkDateDetail = o.eventValue as WorkDateDetail
                        if (workdateDetail.workdate != null) {
                            if (context != null) {
                                setupViewPager(workdatedetailBinding!!.viewPager,workdateDetail.workdate)
                            }
                        }
                    }
                })
    }

    override fun onPause() {
        super.onPause()
    }
    private fun setupViewPager(pager: ViewPager?,workDateData: WorkDate?) {
        val adapter = WorkDateDetailAdapter(childFragmentManager)
        val f1 = WorkDateFragmentDetailSub1()
        adapter.addFragment(f1,"TAB 1")
        val f2 = WorkDateFragmentDetailSub2()
        adapter.addFragment(f2, "TAB 2")
        pager?.adapter = adapter
        f1.setDataOnView(workDateData)
        f2.setDataOnView(workDateData)
    }

    override fun backView() {
    }
}
