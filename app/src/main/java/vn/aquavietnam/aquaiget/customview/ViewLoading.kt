package vn.aquavietnam.aquaiget.customview

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.AttributeSet
import android.view.*
import vn.aquavietnam.aquaiget.R

/**
 * Created by ThanhTuan on 3/30/2018.
 */
class ViewLoading @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_loading, this, false)
        this.addView(view)
    }
    fun showLoading(parentView: ConstraintLayout) {
        parentView.addView(this)
        this.layoutParams = ConstraintLayout.LayoutParams(0,0)
        this.id = R.id.id_vwLoading
        this.elevation = 2F
        val cs = ConstraintSet()
        cs.clone(parentView)
        cs.match(this,parentView)
        cs.applyTo(parentView)
    }
        fun hideLoading(parentView: ConstraintLayout) {
        parentView.removeView(this)
    }
    fun  ConstraintSet.match(view: View, parentView: View) {
        this.connect(view.id, ConstraintSet.TOP, parentView.id, ConstraintSet.TOP)
        this.connect(view.id, ConstraintSet.START, parentView.id, ConstraintSet.START)
        this.connect(view.id, ConstraintSet.END, parentView.id, ConstraintSet.END)
        this.connect(view.id, ConstraintSet.BOTTOM, parentView.id, ConstraintSet.BOTTOM)
    }

}

