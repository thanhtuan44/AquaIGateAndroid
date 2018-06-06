package vn.aquavietnam.aquaiget.homepage.nonscan

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.aquavietnam.aquaiget.NonScanBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.homepage.HomePageFragment
import vn.aquavietnam.aquaiget.model.NonScan
import vn.aquavietnam.aquaiget.model.ResultNonScan
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus
import vn.aquavietnam.aquaiget.service.registerdateoff.RegisterDateOffFragment
import vn.aquavietnam.aquaiget.service.registernonscan.RegisterNonScanFragment
import vn.aquavietnam.aquaiget.service.registerovertime.RegisterOverTimeFragment

class NonScanFragment : BaseBackFragment() {
    private var nonScanBinding: NonScanBinding? = null
    private var nonScanVM: NonScanVM? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (nonScanBinding == null) {
            nonScanBinding = DataBindingUtil.inflate(inflater, R.layout.frag_list_nonscan, container, false)
        }
        return nonScanBinding!!.root
    }
    override fun findData() {
        nonScanVM = NonScanVM(context, null)
        nonScanBinding!!.nonScanVM = nonScanVM
        nonScanVM!!.nonScanAdapter!!.rowSelectDataSwipe = {nonScan,index,value -> Unit
            gotoTypeFragment(nonScan,value)
        }
    }

    override fun callService() {
        showLoading(nonScanBinding!!.cstNonScan)
        nonScanVM!!.getListNonScan()
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_NONSCAN_LIST_EVENT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(nonScanBinding!!.cstNonScan)
                        val listNonScan: ResultNonScan = o.eventValue as ResultNonScan
                        if(listNonScan.status == "1") {
                            if(listNonScan.data!!.isNotEmpty()) {
                                Log.d("DATA NonScan",listNonScan.toString())
                                if (listNonScan.data!!.isNotEmpty()) {
                                    if (context != null) {
                                        nonScanVM!!.setAdapter(listNonScan.data!! as MutableList<NonScan>)
                                    }
                                }
                            }else {
                                showNoData(nonScanBinding!!.cstNonScan,null)
                            }
                        }else {
                            showNoData(nonScanBinding!!.cstNonScan,listNonScan.message)
                        }

                    }
                })

    }

    override fun onPause() {
        super.onPause()
    }
    override fun backView() {
        this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right).replace(R.id.tabbarContent, HomePageFragment(),"HomePageFragment").addToBackStack("HomePageFragment").commit()
    }
    fun gotoTypeFragment(data:Parcelable,toFragment: String) {
        if(data is NonScan) {
            when (toFragment) {
                "DateOff" -> this.gotoFragment(0,data as NonScan)
                "NonScan" -> this.gotoFragment(1,data as NonScan)
                "BussinessTrip" -> this.gotoFragment(2,data as NonScan)
                else -> {
                    Log.d("","")
                }
            }
        }
    }
    fun gotoFragment(postion: Int,nonScanData: NonScan) {
        val bundle = Bundle()
        bundle.putParcelable("NONSCAN_DATA",nonScanData)
        if(postion == 0) {
            val registerDateOffFragment = RegisterDateOffFragment()
            registerDateOffFragment.arguments = bundle
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, registerDateOffFragment,"RegisterDateOffFragment").addToBackStack("RegisterDateOffFragment").commit()
        }else if(postion == 1) {
            val registerNonScanFragment = RegisterNonScanFragment()
            registerNonScanFragment.arguments = bundle
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, registerNonScanFragment,"RegisterOverTimeFragment").addToBackStack("RegisterOverTimeFragment").commit()
        }else if(postion == 2) {
            val registerNonScanFragment = RegisterNonScanFragment()
            registerNonScanFragment.arguments = bundle
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, registerNonScanFragment,"RegisterNonScanFragment").addToBackStack("RegisterNonScanFragment").commit()
        }
    }
}