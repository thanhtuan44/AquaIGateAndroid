package vn.aquavietnam.aquaiget.homepage.bonus

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.homepage.workdate.WorkDateAdapter
import vn.aquavietnam.aquaiget.model.Bonus
import vn.aquavietnam.aquaiget.model.BonusInfo
import vn.aquavietnam.aquaiget.model.WorkDate
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

class BonusVM(context: Context?, bonusInfo: List<BonusInfo>?): BaseVM()  {
    var bonusAdapter: BonusAdapter? = null
    init {
        this.context = context
        bonusAdapter = BonusAdapter(context!!, bonusInfo)
        initRecycler(bonusAdapter!!,1)
    }
    fun setAdapter(data: List<BonusInfo>?) {
        bonusAdapter!!.arrData = data
        bonusAdapter!!.notifyDataSetChanged()

    }
    fun getDataDiligentBonus() {
        AquaService.getDiligentBonusService().execute().subscribe({ resultBonus ->
            if (resultBonus != null) {
                val dataBus = DataBus(DataBus.GET_DILIGENT_BONUS_EVENT, resultBonus)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("BonusFragment", error.message)
        })
    }
}