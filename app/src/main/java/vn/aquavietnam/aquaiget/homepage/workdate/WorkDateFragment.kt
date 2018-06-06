package vn.aquavietnam.aquaiget.homepage.workdate

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.WorkDateBinding
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.homepage.HomePageFragment
import vn.aquavietnam.aquaiget.homepage.workdatedetail.WorkDateDetailFragment
import vn.aquavietnam.aquaiget.model.ListWorkDate
import vn.aquavietnam.aquaiget.model.WorkDate
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus


class WorkDateFragment : BaseBackFragment() {
    private var workDateBinding: WorkDateBinding? = null
    private var workDateVM: WorkDateVM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (workDateBinding == null) {
            workDateBinding = DataBindingUtil.inflate(inflater, R.layout.frag_workdate, container, false)
        }
        return workDateBinding!!.root
    }
    override fun findData() {
        showLoading(workDateBinding!!.cstLayoutWorkDate)
        workDateVM = WorkDateVM(context, null)
        workDateBinding!!.workdateVM = workDateVM
        workDateVM!!.workDateAdapter?.rowSelectWorkDate = {workDate -> Unit
            gotoFragment(workDate)
        }
    }

    override fun callService() {
        workDateVM!!.getListWorkDate()
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_WORKDATE_LIST_EVENT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(workDateBinding!!.cstLayoutWorkDate)
                        val listWorkDate: ListWorkDate = o.eventValue as ListWorkDate
                        Log.d("DATA PRODUCT",listWorkDate.toString())
                        if (listWorkDate.data!!.isNotEmpty()) {
                            if (context != null) {
                                workDateVM!!.setAdapter(listWorkDate.data!! as MutableList<WorkDate>)
                            }
                        }
                    }
                })

    }

    override fun onPause() {
        super.onPause()
    }
    fun gotoFragment(data: String) {
        val bundle = Bundle()
        bundle.putString("BUNDLE_WORKDATE_SELECTED",data)
        val workDateDetailFragment = WorkDateDetailFragment()
        workDateDetailFragment.arguments = bundle
        this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, workDateDetailFragment,"WorkDateDetailFragment").addToBackStack("WorkDateDetailFragment").commit()
    }

    override fun backView() {
        this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right).replace(R.id.tabbarContent, HomePageFragment(),"HomePageFragment").addToBackStack("HomePageFragment").commit()
    }
}
