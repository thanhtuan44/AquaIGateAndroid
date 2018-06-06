package vn.aquavietnam.aquaiget.customview

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.AttributeSet
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.view_edt_with_border.view.*
import vn.aquavietnam.aquaiget.R

/**
 * Created by ThanhTuan on 3/28/2018.
 */
class EditTextWithBorder @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {
    var buttonTapped: ((String) -> Unit)? =  null
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_edt_with_border, this, false)
        addView(view)
        ConstraintSet().clone(this)
        imgIcon.setOnClickListener{
            buttonTapped?.invoke("DATA VALUE")
        }
    }
}
