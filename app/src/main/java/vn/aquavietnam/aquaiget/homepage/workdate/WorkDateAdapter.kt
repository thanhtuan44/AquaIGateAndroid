package vn.aquavietnam.aquaiget.homepage.workdate

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.WorkDateRowBinding
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.WorkDate

/**
 * Created by ThanhTuan on 5/7/2018.
 */
class WorkDateAdapter(var context: Context, private  var arrWorkDate: List<WorkDate>?) : BaseAdapter() {

    var rowSelectWorkDate: ((String) -> Unit)? =  null
    lateinit var workdateRowBinding: WorkDateRowBinding
    public var arrData: List<WorkDate>? = arrWorkDate

    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }

    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        workdateRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_workdate, parent, false)
        return  BindingViewHolder(workdateRowBinding)
    }

    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#201966A7"))
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
        var workdateRowBinding = holder.getBindingViewHolder() as WorkDateRowBinding
        workdateRowBinding.workdate = arrData!![position]
        workdateRowBinding.cstLayoutBaseWorkDate.setOnClickListener {
            rowSelectWorkDate!!(workdateRowBinding.workdate!!.workDate!!)
        }
    }
}