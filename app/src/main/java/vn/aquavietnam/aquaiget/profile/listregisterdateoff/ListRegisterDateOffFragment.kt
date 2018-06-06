package vn.aquavietnam.aquaiget.profile.listregisterdateoff

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.aquavietnam.aquaiget.ListRegisterDateOffBinding
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
class ListRegisterDateOffFragment: BaseBackFragment() {
    private var listRegisterDateOffBinding: ListRegisterDateOffBinding? = null
    private var listRegisterDateOffVM: ListRegisterDateOffVM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (listRegisterDateOffBinding == null) {
            listRegisterDateOffBinding = DataBindingUtil.inflate(inflater, R.layout.frag_list_register_dateoff, container, false)
        }
        return listRegisterDateOffBinding!!.root
    }
    override fun findData() {
        showLoading(listRegisterDateOffBinding!!.cstListRegisterDateOff)
        listRegisterDateOffVM = ListRegisterDateOffVM(context, null)
        listRegisterDateOffBinding!!.listRegisterDateOffVM = listRegisterDateOffVM

    }

    override fun callService() {
        listRegisterDateOffVM!!.getListRegisterDateOff()
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_LIST_REGISTER_DATEOFF)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(listRegisterDateOffBinding!!.cstListRegisterDateOff)
                        val listAbsent: ListAbsent = o.eventValue as ListAbsent
                        if(listAbsent.status == "1") {
                            Log.d("DATA",listAbsent.toString())
                            if (listAbsent.absents!!.isNotEmpty()) {
                                if (context != null) {
                                    listRegisterDateOffVM!!.setAdapter(listAbsent.absents!! as MutableList<Absent>)
                                }
                            }else{
                                showNoData(listRegisterDateOffBinding!!.cstListRegisterDateOff,null)
                            }
                        }else{
                            showNoData(listRegisterDateOffBinding!!.cstListRegisterDateOff,listAbsent.message)
                        }

                    }
                })

    }

    override fun onPause() {
        super.onPause()
    }

    override fun backView() {
        this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right).replace(R.id.tabbarContent, ProfileFragment(),"ProfileFragment").addToBackStack("ProfileFragment").commit()
    }
}