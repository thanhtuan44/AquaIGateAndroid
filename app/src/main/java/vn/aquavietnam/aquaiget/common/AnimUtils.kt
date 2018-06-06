package vn.aquavietnam.aquaiget

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View

/**
 * Created by ThanhTuan on 3/27/2018.
 */
class AnimUtils {
    companion object {
        @JvmStatic
        fun fadeShowView(view: View, duration: Int) {
            view.visibility = View.VISIBLE
            view.alpha = 0f
            ViewCompat.animate(view).alpha(1f).setDuration(duration.toLong()).setListener(null)
        }
        @JvmStatic
        fun fadeHideView(view: View, duration: Int) {
            ViewCompat.animate(view).alpha(0f).setDuration(duration.toLong()).setListener(object : ViewPropertyAnimatorListener {
                override fun onAnimationStart(view: View) {
                    view.isDrawingCacheEnabled = true
                }

                override fun onAnimationEnd(view: View) {
                    view.visibility = View.GONE
                    view.alpha = 1f
                    view.isDrawingCacheEnabled = false
                }

                override fun onAnimationCancel(view: View) {}
            })
        }

    }
}