package vn.aquavietnam.aquaiget.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.header_navigation.view.*
import kotlinx.android.synthetic.main.tabbar_activity.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.support.v4.widget.NestedScrollView
import vn.aquavietnam.aquaiget.customview.ViewLoading
import vn.aquavietnam.aquaiget.customview.ViewNoData


/**
 * Created by ThanhTuan on 3/21/2018.
 */
abstract class BaseFragment : Fragment() {

    var baseDisposable: Disposable? = null
    var vwLoading : ViewLoading? = null
    var vwNodata : ViewNoData? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBaseActivity().bottomTabbar.visibility = View.VISIBLE
        getBaseActivity().nav_Header.iconBackImage.visibility = View.INVISIBLE
        getBaseActivity().nav_Header.iconBack.visibility = View.INVISIBLE
//        loading = ViewLoading(view.context,null,0)
        findData()
    }

    protected abstract fun findData()
    fun getBaseActivity(): TabbarActivity {
        return if (activity is TabbarActivity) {
            activity as TabbarActivity
        } else {
            return TabbarActivity()
        }
    }

    override fun onPause() {
        super.onPause()
        if(baseDisposable != null) {
                baseDisposable!!.dispose()
        }
    }

    override fun onResume() {
        super.onResume()
    }
    fun showLoading(layout: ConstraintLayout) {
        if(vwLoading == null) {
            vwLoading = ViewLoading(getBaseActivity(),null,0)
            vwLoading!!.showLoading(layout)
        }
    }


    fun hideLoading(layout: ConstraintLayout){
        if(vwLoading != null) {
            vwLoading!!.hideLoading(layout)
            vwLoading = null
        }
    }

    fun showNoData(layout: ConstraintLayout,message: String?) {
        if(vwNodata == null) {
            vwNodata = ViewNoData(getBaseActivity(),null,0)
            vwNodata!!.showLoading(layout,message)
        }
    }

}

abstract class BaseBackFragment : BaseFragment() {
    var ticker : Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBaseActivity().bottomTabbar.visibility = View.INVISIBLE
        getBaseActivity().nav_Header.iconBackImage.visibility = View.VISIBLE
        getBaseActivity().nav_Header.iconBack.visibility = View.VISIBLE
        getBaseActivity().nav_Header.iconBack.setOnClickListener {
            backView()
        }
    }
    override fun findData() {

    }
    abstract fun backView()
    abstract fun callService()
    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        var animation: Animation? = super.onCreateAnimation(transit, enter, nextAnim)

        // HW layer support only exists on API 11+
        if (Build.VERSION.SDK_INT >= 11) {
            if (animation == null && nextAnim != 0) {
                animation = AnimationUtils.loadAnimation(activity, nextAnim)
            }

            if (animation != null) {
                view!!.setLayerType(View.LAYER_TYPE_HARDWARE, null)
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                        Log.d("Animation BaseFragment","onAnimationStart")
                    }

                    override fun onAnimationRepeat(animation: Animation?) {
                        Log.d("Animation BaseFragment","onAnimationRepeat")
                    }

                    override fun onAnimationEnd(animation: Animation) {
//                        view!!.setLayerType(View.LAYER_TYPE_NONE, null)
                        Log.d("Animation BaseFragment","onAnimationEnd")
                        if(ticker == 0) {
                            ticker ++
                            callService()
                        }

                    }
                })
            }
        }

        return animation
    }

}


