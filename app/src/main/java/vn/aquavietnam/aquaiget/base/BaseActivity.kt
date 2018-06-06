package vn.aquavietnam.aquaiget.base

import android.app.Activity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.customview.ViewLoading

/**
 * Created by ThanhTuan on 3/30/2018.
 */
abstract class BaseActivity : Activity() {
    var vwLoading : ViewLoading? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
    fun showLoading(layout: ConstraintLayout) {
        if(vwLoading == null) {
            vwLoading = ViewLoading(this,null,0)
            vwLoading!!.showLoading(layout)
        }
    }
    fun hideLoading(layout: ConstraintLayout){
        if(vwLoading != null) {
            vwLoading!!.hideLoading(layout)
        }
    }
}