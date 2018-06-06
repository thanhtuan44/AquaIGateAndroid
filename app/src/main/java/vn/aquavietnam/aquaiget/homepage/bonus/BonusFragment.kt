package vn.aquavietnam.aquaiget.homepage.bonus

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.BonusBinding
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.homepage.HomePageFragment
import vn.aquavietnam.aquaiget.model.Bonus
import vn.aquavietnam.aquaiget.model.BonusInfo
import vn.aquavietnam.aquaiget.model.ResultBonus
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus
import java.util.ArrayList

class BonusFragment(): BaseBackFragment(){

    private var bonusBinding: BonusBinding? = null
    private var bonusVM: BonusVM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (bonusBinding == null) {
            bonusBinding = DataBindingUtil.inflate(inflater, R.layout.frag_list_bonus, container, false)
        }
        return bonusBinding!!.root
    }
    override fun findData() {
        showLoading(bonusBinding!!.cstBonus)
        bonusVM = BonusVM(context, null)
        bonusBinding!!.bonusVM = bonusVM
    }

    override fun callService() {
        bonusVM!!.getDataDiligentBonus()
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_DILIGENT_BONUS_EVENT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(bonusBinding!!.cstBonus)
                        val resultBonus: ResultBonus = o.eventValue as ResultBonus
                        Log.d("DATA PRODUCT",resultBonus.toString())
                        if (resultBonus.data != null) {
                            if (context != null) {
                                bonusVM!!.setAdapter(customData(resultBonus.data!!))
                            }
                        }
                    }
                })

    }

    override fun onPause() {
        super.onPause()
    }
    private fun customData(data: Bonus): List<BonusInfo> {
        val arrBonus = ArrayList<BonusInfo>()

        arrBonus.add(BonusInfo("Chuyên cần",data.bonus!!))
        arrBonus.add(BonusInfo("Đi trễ",data.inLate!!))
        arrBonus.add(BonusInfo("Về sớm",data.outEarly!!))
        arrBonus.add(BonusInfo("Unpaid thời gian",data.unpaidTime!!))
        arrBonus.add(BonusInfo("Không quẹt thẻ",data.noneScan!!))
        arrBonus.add(BonusInfo("LeaveNoBonus",data.leaveNoBonus!!))
        arrBonus.add(BonusInfo("Ngày nghỉ",data.dayOff!!))
        arrBonus.add(BonusInfo("Đổi ca",data.changeShift!!))

        return arrBonus
    }
    override fun backView() {
        this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right).replace(R.id.tabbarContent, HomePageFragment(),"HomePageFragment").addToBackStack("HomePageFragment").commit()
    }
}