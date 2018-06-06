package vn.aquavietnam.aquaiget.common.customview.slider

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.constraint.ConstraintLayout
import android.support.v4.view.PagerAdapter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.AquaSliderBinding
import vn.aquavietnam.aquaiget.model.Slider
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.common.customview.image.AquaImageView

/**
 * Created by ThanhTuan on 3/27/2018.
 */
class AquaSlider(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    var myList: MutableList<Slider> = mutableListOf()
    var sliderPageAdapter: SlidePage<Any?>? = null

    init {
        val rootView: AquaSliderBinding = DataBindingUtil.inflate(inflater, R.layout.view_slider, null, false)
        sliderPageAdapter = SlidePage(context, myList)
        rootView.vpSlider.adapter = sliderPageAdapter
        addView(rootView.root)
    }


    class SlidePage<PageSliderBinding>(var context: Context?, var listPage: List<Slider>) : PagerAdapter() {
        private val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var rowSelectData: ((Int) -> Unit)? =  null
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val slidePageBinding: vn.aquavietnam.aquaiget.PageSliderBinding = DataBindingUtil.inflate(inflater, R.layout.view_slider_page, container, false)
            slidePageBinding.imgUrl = listPage[position].image
            slidePageBinding!!.imgProduct.setOnClickListener {
                rowSelectData!!(position)
            }
            container.addView(slidePageBinding.root)
            return slidePageBinding.root
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object` as AquaImageView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(container)
        }

        override fun getCount(): Int {
            return listPage.size
        }

    }
}