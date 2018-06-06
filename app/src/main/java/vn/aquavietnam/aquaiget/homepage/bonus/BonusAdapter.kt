package vn.aquavietnam.aquaiget.homepage.bonus

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.BonusRowBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.BonusInfo

class BonusAdapter(var context: Context, private  var arrBonusInfo: List<BonusInfo>?) : BaseAdapter() {

    lateinit var bonusRowBinding: BonusRowBinding
    public var arrData: List<BonusInfo>? = arrBonusInfo

    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }

    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        bonusRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_list_bonus, parent, false)
        return  BindingViewHolder(bonusRowBinding)
    }

    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        var bonusRowBinding = holder.getBindingViewHolder() as BonusRowBinding
        bonusRowBinding.bonusInfo = arrData!![position]
        if(position == 0) {
            bonusRowBinding.lblValue.setTextColor(Color.parseColor("#1966A7"))
        }else {
            bonusRowBinding.lblValue.setTextColor(Color.parseColor("#000000"))
        }
    }
}