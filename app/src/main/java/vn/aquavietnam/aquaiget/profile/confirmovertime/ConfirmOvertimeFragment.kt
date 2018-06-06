package vn.aquavietnam.aquaiget.profile.confirmovertime

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.aquavietnam.aquaiget.ConfirmNonScanBinding
import vn.aquavietnam.aquaiget.ConfirmOvertimeBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.model.ListNonScan
import vn.aquavietnam.aquaiget.model.ListOvertime
import vn.aquavietnam.aquaiget.model.MissingNonScan
import vn.aquavietnam.aquaiget.model.Overtime
import vn.aquavietnam.aquaiget.profile.ProfileFragment
import vn.aquavietnam.aquaiget.profile.confirmnonscan.ConfirmNonScanVM
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus


/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ConfirmOvertimeFragment: BaseBackFragment() {
    private var confirmOvertimeBinding: ConfirmOvertimeBinding? = null
    private var confirmOvertimeVM: ConfirmOvertimeVM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (confirmOvertimeBinding == null) {
            confirmOvertimeBinding = DataBindingUtil.inflate(inflater, R.layout.frag_list_confirm_overtime, container, false)
        }
        return confirmOvertimeBinding!!.root
    }
    override fun findData() {
        showLoading(confirmOvertimeBinding!!.cstConfirmOvertime)
        confirmOvertimeVM = ConfirmOvertimeVM(context, null)
        confirmOvertimeBinding!!.confirmOvertimeVM = confirmOvertimeVM

    }

    override fun callService() {
        confirmOvertimeVM!!.getListConfirmOvertime()
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_LIST_CONFIRM_OVERTIME)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(confirmOvertimeBinding!!.cstConfirmOvertime)
                        val listOvertime: ListOvertime = o.eventValue as ListOvertime
                        if(listOvertime.status == "1") {
                            Log.d("DATA",listOvertime.toString())
                            if (listOvertime.data!!.isNotEmpty()) {
                                if (context != null) {
                                    confirmOvertimeVM!!.setAdapter(listOvertime.data!! as ArrayList<Overtime>)
                                }
                            }else{
                                showNoData(confirmOvertimeBinding!!.cstConfirmOvertime,null)
                            }
                        }else{
                            showNoData(confirmOvertimeBinding!!.cstConfirmOvertime,listOvertime.message)
                        }

                    }
                })

    }

    override fun onPause() {
        super.onPause()
    }

    override fun backView() {
        this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.tabbarContent, ProfileFragment(),"ProfileFragment").addToBackStack("ProfileFragment").commit()
    }
}