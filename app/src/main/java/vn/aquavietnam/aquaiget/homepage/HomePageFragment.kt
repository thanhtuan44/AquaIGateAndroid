package vn.aquavietnam.aquaiget.homepage

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.frag_homepage.*
import kotlinx.android.synthetic.main.view_with_text.view.*
import vn.aquavietnam.aquaiget.HomePageBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseFragment
import vn.aquavietnam.aquaiget.homepage.annualleave.AnnualLeaveFragment
import vn.aquavietnam.aquaiget.homepage.bonus.BonusFragment
import vn.aquavietnam.aquaiget.homepage.nonscan.NonScanFragment
import vn.aquavietnam.aquaiget.homepage.workdate.WorkDateFragment
import vn.aquavietnam.aquaiget.model.Home1
import vn.aquavietnam.aquaiget.model.Slider
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.DataBus.Companion.GET_HOME1_EVENT
import vn.aquavietnam.aquaiget.rxjava.RxBus
import vn.aquavietnam.aquaiget.homepage.category.CategoryFragment


class HomePageFragment : BaseFragment() {

    private var homepageBinding: HomePageBinding? = null
    private var homePageVM: HomePageVM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (homepageBinding == null) {
            homepageBinding = DataBindingUtil.inflate(inflater, R.layout.frag_homepage, container, false)
        }

        return homepageBinding!!.root
    }
    override fun findData() {
        showLoading(homepageBinding!!.cstHomePage)
        vwCategoryInfo.txvViewWithText.text = "Category Item"
        rclvCategory.isFocusable = false
        sldBanner.requestFocus()
        homePageVM = HomePageVM(context)
        homePageVM!!.getHome1()
        homepageBinding!!.homePageVM = homePageVM
        homePageVM!!.categoryAdapter.rowSelectOneParameter = {index -> Unit
            gotoFragment(index)
        }
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(GET_HOME1_EVENT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(homepageBinding!!.cstHomePage)
                        val home1: Home1 = o.eventValue as Home1
                        homepageBinding!!.sldBanner?.sliderPageAdapter?.listPage = home1.sliderList as MutableList<Slider>
                        homepageBinding!!.sldBanner?.sliderPageAdapter?.notifyDataSetChanged()
                        homepageBinding!!.sldBanner?.sliderPageAdapter?.rowSelectData = {index -> Unit
                            goToProductFragment(index)
                        }
                    }
                })
    }
    override fun onPause() {
        super.onPause()
    }

    fun gotoFragment(postion: Int) {
        if(postion == 0) {
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, WorkDateFragment(),"WorkDateFragment").addToBackStack("WorkDateFragment").commit()
        }else if(postion == 1){
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, BonusFragment(),"BonusFragment").addToBackStack("BonusFragment").commit()
        }else if(postion == 2){
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, AnnualLeaveFragment(),"AnnualLeaveFragment").addToBackStack("AnnualLeaveFragment").commit()
        }else if(postion == 3){
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, NonScanFragment(),"NonScanFragment").addToBackStack("NonScanFragment").commit()
        }
    }
    fun goToProductFragment(postion: Int){
        val bundle = Bundle()
        bundle.putInt("INDEX",postion + 1)
        val productInfoFragment = CategoryFragment()
        productInfoFragment.arguments = bundle
        this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, productInfoFragment,"CategoryFragment").addToBackStack("CategoryFragment").commit()
    }

//    companion object {
//        fun newInstance(bundle: Bundle?): HomePageFragment {
//            val myFragment = HomePageFragment()
//            myFragment.arguments = bundle
//            return myFragment
//        }
//    }
}

