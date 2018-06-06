package vn.aquavietnam.aquaiget.profile.confirmnonscan

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.aquavietnam.aquaiget.ConfirmNonScanBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.model.ListNonScan
import vn.aquavietnam.aquaiget.model.MissingNonScan
import vn.aquavietnam.aquaiget.profile.ProfileFragment
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ConfirmNonScanFragment: BaseBackFragment() {
    private var confirmNonScanBinding: ConfirmNonScanBinding? = null
    private var confirmNonScanVM: ConfirmNonScanVM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (confirmNonScanBinding == null) {
            confirmNonScanBinding = DataBindingUtil.inflate(inflater, R.layout.frag_list_confirm_nonscan, container, false)
        }
        return confirmNonScanBinding!!.root
    }
    override fun findData() {
        showLoading(confirmNonScanBinding!!.cstConfirmNonScan)
        confirmNonScanVM = ConfirmNonScanVM(context, null)
        confirmNonScanBinding!!.confirmNonScanVM = confirmNonScanVM

    }

    override fun callService() {
        confirmNonScanVM!!.getListConfirmNonScan()
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_LIST_CONFIRM_NONSCAN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(confirmNonScanBinding!!.cstConfirmNonScan)
                        val listNonScan: ListNonScan = o.eventValue as ListNonScan
                        if(listNonScan.status == "1") {
                            Log.d("DATA",listNonScan.toString())
                            if (listNonScan.data!!.isNotEmpty()) {
                                if (context != null) {
                                    confirmNonScanVM!!.setAdapter(listNonScan.data!! as MutableList<MissingNonScan>)
                                }
                            }else{
                                showNoData(confirmNonScanBinding!!.cstConfirmNonScan,null)
                            }
                        }else{
                            showNoData(confirmNonScanBinding!!.cstConfirmNonScan,listNonScan.message)
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