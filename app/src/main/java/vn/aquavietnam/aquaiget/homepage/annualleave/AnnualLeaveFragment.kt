package vn.aquavietnam.aquaiget.homepage.annualleave

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.view_button_arrow.view.*
import vn.aquavietnam.aquaiget.AnnualLeaveBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.model.*
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.homepage.HomePageFragment


class AnnualLeaveFragment : BaseBackFragment() {
    private var annualLeaveBinding: AnnualLeaveBinding? = null
    private var annualLeaveVM: AnnualLeaveVM? = null
    private var annualLeave1VM: AnnualLeave1VM? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (annualLeaveBinding == null) {
            annualLeaveBinding = DataBindingUtil.inflate(inflater, R.layout.frag_annualleave, container, false)
        }
        return annualLeaveBinding!!.root
    }
    override fun findData() {
        showLoading(annualLeaveBinding!!.cstAnnualLeave)
        annualLeaveVM = AnnualLeaveVM(context)
        annualLeave1VM = AnnualLeave1VM(context)
        annualLeaveBinding!!.annualLeaveVM = annualLeaveVM
        annualLeaveBinding!!.annualLeave1VM = annualLeave1VM
        annualLeaveBinding!!.rclvLastYear!!.visibility = View.GONE
        annualLeaveBinding!!.btnLastYear!!.txvInfo.visibility = View.GONE
        annualLeaveBinding!!.btnLastYear!!.setOnClickListener {
            if(annualLeaveBinding!!.rclvLastYear!!.visibility == View.GONE){
                annualLeaveBinding!!.rclvLastYear!!.visibility = View.VISIBLE
            }else{
                annualLeaveBinding!!.rclvLastYear!!.visibility = View.GONE
            }
        }
        annualLeaveBinding!!.btnCurrentYear!!.setOnClickListener {
            if(annualLeaveBinding!!.rclvCurrentYear!!.visibility == View.GONE){
                annualLeaveBinding!!.rclvCurrentYear!!.visibility = View.VISIBLE
            }else{
                annualLeaveBinding!!.rclvCurrentYear!!.visibility = View.GONE
            }

        }
    }
    override fun callService() {
        annualLeaveVM!!.getListAnnualLeave()
        annualLeave1VM!!.getListAnnualLeave()
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_ANNUALLEAVE_LIST_EVENT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ o ->
                if (o is DataBus) {
                    hideLoading(annualLeaveBinding!!.cstAnnualLeave)
                    val listAnnualLeave: ResultAnnualLeave = o.eventValue as ResultAnnualLeave
                    Log.d("Data Annual Leave",listAnnualLeave.toString())
                    if (listAnnualLeave.data!!.isNotEmpty()) {
                        if (context != null) {
                            var list = ArrayList<AnnualLeave>()
                            for((index,value) in listAnnualLeave.data!!.withIndex()){
                                annualLeaveBinding!!.btnLastYear!!.visibility = View.GONE
                                if(index == 0){
                                    annualLeaveBinding!!.btnCurrentYear!!.txvInfo.text = listAnnualLeave.data!![index].currentYear
                                    annualLeaveVM!!.setAdapter(customData(value))
                                }else if(index == 1){
                                    annualLeaveBinding!!.btnLastYear!!.visibility = View.VISIBLE
                                    annualLeaveBinding!!.btnLastYear!!.txvInfo.visibility = View.VISIBLE
                                    annualLeave1VM!!.setAdapter(customData(value))
                                    annualLeaveBinding!!.btnLastYear!!.txvInfo.text = listAnnualLeave.data!![index].currentYear
                                }
                            }
                        }
                    }
                }
            })
    }

    override fun onPause() {
        super.onPause()
    }
    private fun customData(data: AnnualLeave): List<AnnualLeaveInfo> {
        val arrAnnualLeave = ArrayList<AnnualLeaveInfo>()
        arrAnnualLeave.add(AnnualLeaveInfo("Tổng ngày phép trong năm",data.totalEntitlement!!))
        arrAnnualLeave.add(AnnualLeaveInfo("Ngày phép thêm",data.additionalEarn!!))
        arrAnnualLeave.add(AnnualLeaveInfo("Năm trước",data.lastYear!!))
        arrAnnualLeave.add(AnnualLeaveInfo("Ngày phép trong năm hiện có",data.earnedLeave!!))
        arrAnnualLeave.add(AnnualLeaveInfo("Ngày phép trong năm đã dùng",data.leaveTaken!!))
        arrAnnualLeave.add(AnnualLeaveInfo("Ngày phép còn lại trong năm",data.balance!!))
        return arrAnnualLeave
    }
    override fun backView() {
        this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right).replace(R.id.tabbarContent, HomePageFragment(),"HomePageFragment").addToBackStack("HomePageFragment").commit()
    }
}