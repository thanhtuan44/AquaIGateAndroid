package vn.aquavietnam.aquaiget.profile.listregisterovertime

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.aquavietnam.aquaiget.ListRegisterOvertimeBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.model.ListOvertime
import vn.aquavietnam.aquaiget.model.Overtime
import vn.aquavietnam.aquaiget.profile.ProfileFragment
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/23/2018.
 */
class ListRegisterOvertimeFragment: BaseBackFragment() {
    private var listRegisterOvertimeBinding: ListRegisterOvertimeBinding? = null
    private var listRegisterOvertimeVM: ListRegisterOvertimeVM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (listRegisterOvertimeBinding == null) {
            listRegisterOvertimeBinding = DataBindingUtil.inflate(inflater, R.layout.frag_list_register_overtime, container, false)
        }
        return listRegisterOvertimeBinding!!.root
    }
    override fun findData() {
        showLoading(listRegisterOvertimeBinding!!.cstListRegisterOvertime)
        listRegisterOvertimeVM = ListRegisterOvertimeVM(context, null)
        listRegisterOvertimeBinding!!.listRegisterOvertimeVM = listRegisterOvertimeVM

    }

    override fun callService() {
        listRegisterOvertimeVM!!.getListRegisterOvertime()
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_LIST_REGISTER_OVERTIME)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(listRegisterOvertimeBinding!!.cstListRegisterOvertime)
                        val listOvertime: ListOvertime = o.eventValue as ListOvertime
                        if(listOvertime.status == "1") {
                            Log.d("DATA",listOvertime.toString())
                            if (listOvertime.data!!.isNotEmpty()) {
                                if (context != null) {
                                    listRegisterOvertimeVM!!.setAdapter(listOvertime.data!! as MutableList<Overtime>)
                                }
                            }else{
                                showNoData(listRegisterOvertimeBinding!!.cstListRegisterOvertime,null)
                            }
                        }else{
                            showNoData(listRegisterOvertimeBinding!!.cstListRegisterOvertime,listOvertime.message)
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