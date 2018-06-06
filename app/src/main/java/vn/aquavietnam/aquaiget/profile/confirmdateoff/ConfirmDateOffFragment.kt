package vn.aquavietnam.aquaiget.profile.confirmdateoff

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.aquavietnam.aquaiget.ConfirmDateOffBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.model.Absent
import vn.aquavietnam.aquaiget.model.ListAbsent
import vn.aquavietnam.aquaiget.profile.ProfileFragment
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ConfirmDateOffFragment: BaseBackFragment() {
    private var confirmDateOffBinding: ConfirmDateOffBinding? = null
    private var confirmDateOffVM: ConfirmDateOffVM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (confirmDateOffBinding == null) {
            confirmDateOffBinding = DataBindingUtil.inflate(inflater, R.layout.frag_list_confirm_dateoff, container, false)
        }
        return confirmDateOffBinding!!.root
    }
    override fun findData() {
        showLoading(confirmDateOffBinding!!.cstConfirmDateOff)
        confirmDateOffVM = ConfirmDateOffVM(context, null)
        confirmDateOffBinding!!.confirmDateOffVM = confirmDateOffVM
        confirmDateOffVM!!.confirmDateOffAdapter?.rowSelectOneParameter = {indexRow ->
            confirmDateOffVM!!.confirmDateOffAdapter?.arrData?.removeAt(indexRow)
            confirmDateOffVM!!.confirmDateOffAdapter?.notifyDataSetChanged()
            if(confirmDateOffVM!!.confirmDateOffAdapter?.arrData?.size == 0) {
                showNoData(confirmDateOffBinding!!.cstConfirmDateOff,null)
            }
        }
    }

    override fun callService() {
        confirmDateOffVM!!.getListConfirmDateOff()
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_LIST_CONFIRM_DATEOFF)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(confirmDateOffBinding!!.cstConfirmDateOff)
                        val listAbsent: ListAbsent = o.eventValue as ListAbsent
                        if(listAbsent.status == "1") {
                            Log.d("DATA",listAbsent.toString())
                            if (listAbsent.absents!!.isNotEmpty()) {
                                if (context != null) {
                                    confirmDateOffVM!!.setAdapter(listAbsent.absents!! as ArrayList<Absent>)
                                }
                            }else{
                                showNoData(confirmDateOffBinding!!.cstConfirmDateOff,null)
                            }
                        }else{
                            showNoData(confirmDateOffBinding!!.cstConfirmDateOff,listAbsent.message)
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