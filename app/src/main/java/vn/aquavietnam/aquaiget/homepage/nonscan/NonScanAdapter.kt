package vn.aquavietnam.aquaiget.homepage.nonscan

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.daimajia.swipe.SwipeLayout
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.NonScanRowBinding
import vn.aquavietnam.aquaiget.base.BaseAdapter
import vn.aquavietnam.aquaiget.model.NonScan


class NonScanAdapter(var context: Context, private  var arrNonScan: List<NonScan>?) : BaseAdapter(){

    lateinit var nonScanRowBinding: NonScanRowBinding
    public var arrData: List<NonScan>? = arrNonScan
    override fun itemCount(): Int {
        return if (arrData != null) {
            arrData!!.size
        } else {
            0
        }
    }
    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        nonScanRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_nonscan, parent, false)
        return  BindingViewHolder(nonScanRowBinding)
    }
    override fun bindBaseViewHolder(holder: BindingViewHolder, position: Int) {
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#201966A7"))
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }
        holder.itemView.setBackgroundColor(Color.WHITE);
        var nonScanRowBinding = holder.getBindingViewHolder() as NonScanRowBinding
        if(arrData!![position].missingNonscan == "Nonscan"){
            arrData!![position].timeIn = "Nonscan"
            arrData!![position].timeOut = "Nonscan"
        }
        nonScanRowBinding.nonScan = arrData!![position]
        nonScanRowBinding.btnDateOff.setOnClickListener {
            rowSelectDataSwipe?.invoke(nonScanRowBinding.nonScan!!,position,"DateOff")
            Log.d("btnDateOff Select",position.toString())
        }
        nonScanRowBinding.btnNonScan.setOnClickListener {
            rowSelectDataSwipe?.invoke(nonScanRowBinding.nonScan!!,position,"NonScan")
            Log.d("btnNonScan Select",position.toString())
        }
        nonScanRowBinding.btnBussinessTrip.setOnClickListener {
            rowSelectDataSwipe?.invoke(nonScanRowBinding.nonScan!!,position,"BussinessTrip")
            Log.d("btnBussinessTrip Select",position.toString())
        }
        nonScanRowBinding.swipeLayout.addSwipeListener(object : SwipeLayout.SwipeListener {
            override fun onClose(layout: SwipeLayout) {
                //when the SurfaceView totally cover the BottomView.
            }

            override fun onUpdate(layout: SwipeLayout, leftOffset: Int, topOffset: Int) {
                //you are swiping.
            }

            override fun onStartOpen(layout: SwipeLayout) {

            }

            override fun onOpen(layout: SwipeLayout) {
                //when the BottomView totally show.
            }

            override fun onStartClose(layout: SwipeLayout) {

            }

            override fun onHandRelease(layout: SwipeLayout, xvel: Float, yvel: Float) {
                //when user's hand released.
            }
        })
    }
}