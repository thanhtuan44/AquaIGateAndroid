package vn.aquavietnam.aquaiget.profile.listregisternonscan

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.aquavietnam.aquaiget.ListRegisterNonScanBinding
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
class ListRegisterNonScanFragment: BaseBackFragment() {
    private var listRegisterNonScanBinding: ListRegisterNonScanBinding? = null
    private var listRegisterNonScanVM: ListRegisterNonScanVM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (listRegisterNonScanBinding == null) {
            listRegisterNonScanBinding = DataBindingUtil.inflate(inflater, R.layout.frag_list_register_nonscan, container, false)
        }
        return listRegisterNonScanBinding!!.root
    }
    override fun findData() {
        showLoading(listRegisterNonScanBinding!!.cstListRegisterNonScan)
        listRegisterNonScanVM = ListRegisterNonScanVM(context, null)
        listRegisterNonScanBinding!!.listRegisterNonScanVM = listRegisterNonScanVM

    }

    override fun callService() {
        listRegisterNonScanVM!!.getListRegisterNonScan()
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_LIST_REGISTER_NONSCAN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(listRegisterNonScanBinding!!.cstListRegisterNonScan)
                        val listNonScan: ListNonScan = o.eventValue as ListNonScan
                        if(listNonScan.status == "1") {
                            Log.d("DATA",listNonScan.toString())
                            if (listNonScan.data!!.isNotEmpty()) {
                                if (context != null) {
                                    listRegisterNonScanVM!!.setAdapter(listNonScan.data!! as MutableList<MissingNonScan>)
                                }
                            }else{
                                showNoData(listRegisterNonScanBinding!!.cstListRegisterNonScan,null)
                            }
                        }else{
                            showNoData(listRegisterNonScanBinding!!.cstListRegisterNonScan,listNonScan.message)
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