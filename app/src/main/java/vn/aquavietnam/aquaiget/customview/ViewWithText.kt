package vn.aquavietnam.aquaiget.customview

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.AttributeSet
import android.view.LayoutInflater
import vn.aquavietnam.aquaiget.R

/**
 * Created by ThanhTuan on 5/4/2018.
 */
class ViewWithText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_with_text, this, false)
        addView(view)
        ConstraintSet().clone(this)
//        set.match(view, this)
    }
}