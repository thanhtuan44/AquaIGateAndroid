package vn.aquavietnam.aquaiget.customview

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.AttributeSet
import android.view.LayoutInflater
import vn.aquavietnam.aquaiget.R


class ButtonWithArrow @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_button_arrow, this, false)
        addView(view)
        ConstraintSet().clone(this)
//        set.match(view, this)
    }
}
/*
fun  ConstraintSet.match(view: View, parentView: View) {
    this.connect(view.id, ConstraintSet.TOP, parentView.id, ConstraintSet.TOP)
    this.connect(view.id, ConstraintSet.START, parentView.id, ConstraintSet.START)
    this.connect(view.id, ConstraintSet.END, parentView.id, ConstraintSet.END)
    this.connect(view.id, ConstraintSet.BOTTOM, parentView.id, ConstraintSet.BOTTOM)
}
*/
